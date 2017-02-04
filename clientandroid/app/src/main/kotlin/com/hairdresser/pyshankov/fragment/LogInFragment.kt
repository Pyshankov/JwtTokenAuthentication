package com.hairdresser.pyshankov.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.hairdresser.pyshankov.FragmentFactory

import com.hairdresser.pyshankov.activity.GlobalActivity
import com.hairdresser.pyshankov.hairdresser.R
import com.hairdresser.pyshankov.service.ApiService
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.Charset


class LogInFragment : NamedFragment() {

    private var username: EditText? = null

    private var password: EditText? = null

    private var loginError: TextView? = null

    private var btnLogin: Button? = null

    private var progressBar: ProgressBar? = null


    init {
        this.name = NAME
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_log_in, container, false)

        username = view.findViewById(R.id.editUserName) as EditText
        password = view.findViewById(R.id.editPassword) as EditText
        loginError = view.findViewById(R.id.txtLoginError) as TextView
        btnLogin = view.findViewById(R.id.btnLogin) as Button
        progressBar = view.findViewById(R.id.loginProgressBar) as ProgressBar

        btnLogin!!.setOnClickListener(View.OnClickListener {

            val credentials: JSONObject = JSONObject()
            credentials.put("userName",username!!.text)
            credentials.put("password",password!!.text)
            progressBar!!.visibility=View.VISIBLE

            GlobalActivity.apiService!!.post(
                    ApiService.BASIC_URL+"auth/login",
                    credentials.toString(),
                    {
                        result,responce->
                        val token:String = responce.httpResponseHeaders.get("Authorization")!!.get(0);
                        GlobalActivity.tokenService!!.addToken(token)
                        loginError!!.visibility = View.INVISIBLE
                        progressBar!!.visibility = View.INVISIBLE
                        replaceFragment(FragmentFactory.getFragment(SearchFragment.NAME))
                    },
                    {
                        result->
                        progressBar!!.visibility = View.INVISIBLE
                        var responce: JSONObject?=null
                        try {
                            result.get()
                        } catch(ex:Exception) {
                            when(ex) {
                                is FuelError ->{
                                    when(ex.exception){
                                        is  UnknownHostException-> {
                                        responce = JSONObject();
                                        responce.put("message","Internet connection is not enabled")
                                    }
                                        is SocketTimeoutException->{
                                            responce = JSONObject();
                                            responce.put("message","timeout")
                                        }
                                        else->  responce = JSONObject(String((result as Result.Failure).getException().errorData, Charset.forName("UTF-8")))
                                    }
                                }
                                else ->{
                                    responce = JSONObject();
                                    responce.put("message","unknown error")
                                }
                            }
                        }
                        loginError!!.setText(responce!!.getString("message"))
                        loginError!!.visibility = View.VISIBLE
                    }
            )
        })

        username!!.setText("pyshankov@gmail.com")
        password!!.setText("123321")
        return view
    }

    fun replaceFragment(fragment: Fragment?) {
        if(this.activity!=null) {
            val fragmentManager = this.activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment, "HELLO")
            fragmentTransaction.commit()
        }
    }


    override fun onResume() {
        super.onResume()
        GlobalActivity.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        GlobalActivity.enableDrawer()
    }

    companion object {
        val NAME = "LogIn"
    }


}

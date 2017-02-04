package com.hairdresser.pyshankov.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hairdresser.pyshankov.activity.GlobalActivity
import com.hairdresser.pyshankov.hairdresser.R
import com.hairdresser.pyshankov.service.ApiService

/**
 * Created by pyshankov on 2/4/17.
 */
class ProfileFragment : NamedFragment() {
    init {
        this.name = NAME
    }

    private var profile:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_profile, container, false)
        profile = view.findViewById(R.id.txtProfile) as TextView

        GlobalActivity.apiService!!.get(ApiService.BASIC_URL+"api/user",
                {
                    result->
                     profile!!.setText(result.get())
                },
                {
                    result->
                    profile!!.setText("could not load profile information")
                }
                )
        return view;
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        val NAME = "Profile"
    }


}

package com.hairdresser.pyshankov.service

import android.widget.TextView
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import java.util.*

/**
 * Created by pyshankov on 2/4/17.
 */

class ApiService(private var tokenService: TokenService){


    public fun get(url:String, success: (Result<String, FuelError>) -> Unit, failure: (Result<String, FuelError>) -> Unit){
        val authorization:Pair<String,String> = Pair("Authorization",tokenService!!.token)
        val contentType:Pair<String,String> = Pair("Content-Type","application/json")
        url.httpGet().header(contentType,authorization).responseString { request, response, result ->
            response
            when (result) {
                is Result.Failure -> {
                    failure.invoke(result);
                }
                is Result.Success -> {
                    success.invoke(result);
                }
            }
        }
    }

    public fun post(url:String, payload:String, success: (Result<String, FuelError>, Response) -> Unit, failure: (Result<String, FuelError>) -> Unit){
        val contentType:Pair<String,String> = Pair("Content-Type","application/json")
        url.httpPost().timeout(3000).body(payload).header(contentType).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    failure.invoke(result);
                }
                is Result.Success -> {
                    success.invoke(result,response);
                }
            }
        }
    }

    companion object {
        public var BASIC_URL:String = "http://ec2-35-167-244-237.us-west-2.compute.amazonaws.com:8080/mobile/"
    }

}

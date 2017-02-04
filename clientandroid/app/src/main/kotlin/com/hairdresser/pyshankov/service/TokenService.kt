package com.hairdresser.pyshankov.service

import android.content.Context
import android.content.SharedPreferences

import com.hairdresser.pyshankov.hairdresser.R


/**
 * Created by pyshankov on 1/29/17.
 */

class TokenService(private val context: Context) {

    private val sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE)
    }

    public fun addToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString(TOKEN, token)
        editor.commit()
    }

    val token: String
        get() = sharedPref.getString(TOKEN, "")

    companion object {

        private val TOKEN = "token"
        private val TOKEN_FILE = "token_file"
    }

}

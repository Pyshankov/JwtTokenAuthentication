package com.hairdresser.pyshankov.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.hairdresser.pyshankov.hairdresser.R;


/**
 * Created by pyshankov on 1/29/17.
 */

public class TokenService {

    private Context context;

    private static final String TOKEN = "token";
    private static final String TOKEN_FILE = "token_file";

    private SharedPreferences sharedPref;

    public TokenService(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(TOKEN_FILE,Context.MODE_PRIVATE);
    }

    public void addToken(String token){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        return sharedPref.getString(TOKEN,null);
    }

}

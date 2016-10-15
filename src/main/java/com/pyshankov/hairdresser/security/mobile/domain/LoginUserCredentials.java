package com.pyshankov.hairdresser.security.mobile.domain;

/**
 * Created by pyshankov on 14.10.2016.
 */
public class LoginUserCredentials {

    private String userName;

    private String password;

    public LoginUserCredentials(){}

    public LoginUserCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.pyshankov.hairdresser.dto;

/**
 * Created by pyshankov on 14.10.2016.
 */
public class LoginUserCredentialsDto {

    private String userName;

    private String password;

    public LoginUserCredentialsDto(){}

    public LoginUserCredentialsDto(String userName, String password) {
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

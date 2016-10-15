package com.pyshankov.hairdresser.security.mobile.repository;

/**
 * Created by pyshankov on 14.10.2016.
 */
public interface JwtTokenRepository {
    void addToken(String userName, String token);
    String getToken(String userName);
    String deleteToken(String userName);
}

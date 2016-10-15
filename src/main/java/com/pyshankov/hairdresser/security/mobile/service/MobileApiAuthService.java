package com.pyshankov.hairdresser.security.mobile.service;

import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.security.mobile.domain.LoginUserCredentials;
import java.util.Map;

/**
 * Created by pyshankov on 16.10.2016.
 */
public interface MobileApiAuthService {

    String login(LoginUserCredentials userCredentials);

    String signup(User user);

    Map<String,String> checkTokenValidity(String token);

    void invalidateToken(String token);

}

package com.pyshankov.hairdresser.security.mobile.service;

import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.dto.LoginUserCredentialsDto;
import java.util.Map;

/**
 * Created by pyshankov on 16.10.2016.
 */
public interface MobileApiAuthService {

    String login(LoginUserCredentialsDto userCredentials);

    String signup(User user);

    Map<String,String> checkTokenValidity(String token);

    void invalidateToken(String token);

}

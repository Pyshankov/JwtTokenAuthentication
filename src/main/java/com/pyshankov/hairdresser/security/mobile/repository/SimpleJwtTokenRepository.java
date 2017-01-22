package com.pyshankov.hairdresser.security.mobile.repository;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pyshankov on 14.10.2016.
 */
@Service
public class SimpleJwtTokenRepository implements JwtTokenRepository {

    private Map<String,String> tokens = new ConcurrentHashMap<>();

    @Override
    public void addToken(String userName, String token) {
        tokens.put(userName,token);
    }

    @Override
    public String getToken(String userName) {
        return tokens.get(userName);
    }

    @Override
    public String deleteToken(String userName) {
        return tokens.remove(userName);
    }

    @PostConstruct
    public void postConstruct(){
        tokens.put("pyshankov","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJweXNoYW5rb3YiLCJyb2xlIjoiQURNSU4iLCJleHAiOjE4MDA2Mjg1ODh9.bbC05vkFAHIXikeYs3p0owWDRDHuQqYpf7gHAGPWgx4");
    }

}

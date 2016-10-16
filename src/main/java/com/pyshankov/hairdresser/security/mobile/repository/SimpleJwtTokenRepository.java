package com.pyshankov.hairdresser.security.mobile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
}

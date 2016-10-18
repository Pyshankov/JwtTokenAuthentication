package com.pyshankov.hairdresser.security.mobile.repository;



import com.pyshankov.hairdresser.domain.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by pyshankov on 14.10.2016.
 */
@Service
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.validity.years}")
    private long tokenValidityYears;

    public String createJwtToken(User user){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        LocalDateTime now = LocalDateTime.now();
        //token validity 10 years
        LocalDateTime expiration = now.plusYears(tokenValidityYears);

        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("role", user.getRole().toString());
        claims.setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()));

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm,secretKey);
        return builder.compact();
    }

    public  User parseJwtToken(String jwt){
        Claims claims;

        claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();

        User user = new User();
        user.setRole(User.Role.valueOf(claims.get("role").toString()));
        user.setUserName(claims.getSubject());
        return user;
    }

}

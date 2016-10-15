package com.pyshankov.hairdresser.security.mobile.controller;

import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.exception.UserConstraintException;
import com.pyshankov.hairdresser.security.mobile.domain.LoginUserCredentials;
import com.pyshankov.hairdresser.security.mobile.service.MobileApiAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Controller
public class MobileApiAuthController {

    @Autowired
    private MobileApiAuthService mobileApiAuthService;


    @RequestMapping(value = "mobile/auth/login",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String,String> login(@RequestBody LoginUserCredentials userCredentials, HttpServletResponse httpServletResponse){
        String token = mobileApiAuthService.login(userCredentials);

        httpServletResponse.addHeader("Authorization",token);
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.OK.toString());
        return body;
    }

    @RequestMapping(value = "mobile/auth/signup",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String,String> signup(@RequestBody User user, HttpServletResponse httpServletResponse){
        String token = mobileApiAuthService.signup(user);
        httpServletResponse.addHeader("Authorization",token);
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.CREATED.toString());
        return body;
    }

    @RequestMapping(value = "mobile/api/auth/invalidateToken",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String,String> invalidateToken(HttpServletRequest request){
        mobileApiAuthService.invalidateToken(request.getHeader("Authorization"));
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.OK.toString());
        body.put("tokenValidity","false");
        return body;
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String,String> handleSecurityException(AuthenticationException ex){
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.UNAUTHORIZED.toString());
        body.put("message","Bad Credentials");
        return body;
    }

    @ExceptionHandler(UserConstraintException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String,String> handleUserConstraintException(UserConstraintException ex){
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.CONFLICT.toString());
        body.put("message",ex.getMessage());
        return body;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,String> handleException(Exception ex){
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST.toString());
        body.put("message",HttpStatus.BAD_REQUEST.getReasonPhrase());
        return body;
    }


}

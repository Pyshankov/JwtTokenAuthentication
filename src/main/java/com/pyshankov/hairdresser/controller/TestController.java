package com.pyshankov.hairdresser.controller;

import com.pyshankov.hairdresser.security.mobile.MobileApiSecurityConfig;
import com.pyshankov.hairdresser.security.mobile.service.MobileAuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Controller
public class TestController {


    @RequestMapping(value = "mobile/api/user",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String user(){
           return MobileAuthServiceImpl.getCurrentPrincipal().getUsername();
    }

}

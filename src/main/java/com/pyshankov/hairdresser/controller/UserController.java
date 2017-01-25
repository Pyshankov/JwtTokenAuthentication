package com.pyshankov.hairdresser.controller;


import com.pyshankov.hairdresser.domain.Account;
import com.pyshankov.hairdresser.domain.AccountType;
import com.pyshankov.hairdresser.domain.Location;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.dto.CreateAccountDto;
import com.pyshankov.hairdresser.dto.RequestAccountsDto;
import com.pyshankov.hairdresser.dto.ResponseAccountsDto;
import com.pyshankov.hairdresser.exception.UserConstraintException;
import com.pyshankov.hairdresser.exception.ValidationException;
import com.pyshankov.hairdresser.security.mobile.service.MobileAuthServiceImpl;
import com.pyshankov.hairdresser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService repository;


    @RequestMapping(value = "mobile/api/user",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User user(){
        return repository.findByUserName(MobileAuthServiceImpl.getCurrentPrincipal().getUsername());
    }

//    @RequestMapping(value = "mobile/api/hairdresser",method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Iterable<Account> getAvailableFreelancers(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam(required = false,defaultValue = "10") Double distance){
////        inject current user location, and find all freelance account in specified range
//        Location currentUserLocation = new Location(longitude,latitude);
//        return repository.findNearestAccountInRange(currentUserLocation,AccountType.FREELANCE,distance);
//    }

    @RequestMapping(value = "mobile/api/hairdresser",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseAccountsDto getAvailableFreelancersWithOffsetAndLimit(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam(required = false,defaultValue = "10") Double distance,
            @RequestParam(required = false,defaultValue = "0") Integer offset,
            @RequestParam(required = false,defaultValue = "0")  Integer limit
            ){
//        inject current user location, and find all freelance account in specified range
        Location currentUserLocation = new Location(longitude,latitude);
        return repository.findNearestAccountInRange(currentUserLocation,AccountType.FREELANCE,distance,offset,limit);
    }

    @RequestMapping(value = "mobile/api/createAccount/{userName}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createAccountForUser(@PathVariable String userName, @RequestBody CreateAccountDto createAccountDto){
        repository.createAccountForUser(userName,createAccountDto.fromDto());
    }

    @RequestMapping(value = "mobile/api/dropAccount",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void dropAccountForUser(){
        repository.dropAccountForUser(MobileAuthServiceImpl.getCurrentPrincipal().getUsername());
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

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,String> handleValidationException(ValidationException ex){
        Map<String,String> body = new HashMap<>();
        body.put("status",HttpStatus.BAD_REQUEST.toString());
        body.put("message",ex.getMessage());
        return body;
    }



}

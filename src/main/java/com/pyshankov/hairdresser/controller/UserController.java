package com.pyshankov.hairdresser.controller;


import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.Account;
import com.pyshankov.hairdresser.domain.AccountType;
import com.pyshankov.hairdresser.domain.Location;
import com.pyshankov.hairdresser.dto.CreateAccountDto;
import com.pyshankov.hairdresser.repository.UserRepository;
import com.pyshankov.hairdresser.security.mobile.service.MobileAuthServiceImpl;
import com.pyshankov.hairdresser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public String user(){
           return MobileAuthServiceImpl.getCurrentPrincipal().getUsername();
    }

    @RequestMapping(value = "mobile/api/hairdresser",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Account> getAvailableFreelancers(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Double distance){
        Location currentUserLocation = new Location(longitude,latitude);

//        inject current user location, and find all freelance account in specified range
        return repository.findNearestAccountInRange(currentUserLocation,AccountType.FREELANCE,distance);
    }

    @RequestMapping(value = "mobile/api/createAccount/{userName}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createAccountForUser(@PathVariable String userName, @RequestBody CreateAccountDto createAccountDto){
         repository.createAccountForUser(userName,null);
    }


}

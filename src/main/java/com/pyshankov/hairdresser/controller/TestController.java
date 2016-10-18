package com.pyshankov.hairdresser.controller;


import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.AccountType;
import com.pyshankov.hairdresser.domain.FreelanceAccount;
import com.pyshankov.hairdresser.domain.Location;
import com.pyshankov.hairdresser.repository.AccountRepository;
import com.pyshankov.hairdresser.security.mobile.service.MobileAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Controller
public class TestController {

    @Autowired
    private AccountRepository accountRepository;


    @RequestMapping(value = "mobile/api/user",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String user(){
           return MobileAuthServiceImpl.getCurrentPrincipal().getUsername();
    }

    @RequestMapping(value = "mobile/api/hairdresser",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<AbstractAccount> getAvailableFreelancers(Location currentUserLocation){
//        inject current user location, and find all freelance account in specified range
        return accountRepository.findByAccountType(AccountType.FREELANCE);
    }


}

package com.pyshankov.hairdresser.dto;


import com.pyshankov.hairdresser.domain.*;

import java.util.Arrays;

/**
 * Created by pyshankov on 1/22/17.
 */
public class CreateAccountDto  {

    private AccountType accountType;

    private String fullName;

    private String phone;

    private Location location;

    private String serviceList;


    public CreateAccountDto() {
    }

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Account fromDto(){
        Account account = null;
        if (getAccountType().equals(AccountType.CUSTOMER)){
            account = new CustomerAccount(getFullName(),getPhone());
        }
        else if(getAccountType().equals(AccountType.FREELANCE)){
            account = new FreelanceAccount(getFullName(),getPhone());
            ((FreelanceAccount) account).setServicesList(Arrays.asList(getServiceList().split(",")));
        }
        return account;
    }
}

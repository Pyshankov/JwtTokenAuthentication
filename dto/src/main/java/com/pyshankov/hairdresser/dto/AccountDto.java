package com.pyshankov.hairdresser.dto;




import java.util.Arrays;

/**
 * Created by pyshankov on 1/22/17.
 */
public class AccountDto {

    private AccountType accountType;

    private String fullName;

    private String phone;

    private Location location;

    private String serviceList;


    public AccountDto() {
    }

    public AccountDto(AccountType accountType, String fullName, String phone, Location location) {
        this.accountType = accountType;
        this.fullName = fullName;
        this.phone = phone;
        this.location = location;
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


}

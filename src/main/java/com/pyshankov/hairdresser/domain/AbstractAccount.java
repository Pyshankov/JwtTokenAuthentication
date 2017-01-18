package com.pyshankov.hairdresser.domain;


/**
 * Created by pyshankov on 16.10.2016.
 */

public abstract class AbstractAccount implements Account {


    protected AccountType accountType;

    protected String fullName;

    protected String phone;

    protected Location location;

    public AbstractAccount(){
    }

    public AbstractAccount(AccountType accountType, String fullName, String phone) {
        this.accountType = accountType;
        this.fullName = fullName;
        this.phone = phone;
    }
    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

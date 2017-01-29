package com.pyshankov.hairdresser.domain;


import com.pyshankov.hairdresser.dto.AccountType;
import com.pyshankov.hairdresser.dto.Location;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractAccount that = (AbstractAccount) o;

        if (getAccountType() != that.getAccountType()) return false;
        if (getFullName() != null ? !getFullName().equals(that.getFullName()) : that.getFullName() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
        return getLocation() != null ? getLocation().equals(that.getLocation()) : that.getLocation() == null;
    }

    @Override
    public int hashCode() {
        int result = getAccountType() != null ? getAccountType().hashCode() : 0;
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractAccount{" +
                "accountType=" + accountType +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", location=" + location +
                '}';

    }

}

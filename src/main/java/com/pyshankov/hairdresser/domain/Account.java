package com.pyshankov.hairdresser.domain;

/**
 * Created by pyshankov on 16.10.2016.
 */
public interface Account {

    long getId();

    AccountType getAccountType();

    String getFullName();

    Location getLocation();

}

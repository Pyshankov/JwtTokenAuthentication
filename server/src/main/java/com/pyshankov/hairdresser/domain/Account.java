package com.pyshankov.hairdresser.domain;

import com.pyshankov.hairdresser.dto.AccountType;
import com.pyshankov.hairdresser.dto.Location;

/**
 * Created by pyshankov on 16.10.2016.
 */
public interface Account {

    AccountType getAccountType();

    String getFullName();

    Location getLocation();

    String getPhone();

}

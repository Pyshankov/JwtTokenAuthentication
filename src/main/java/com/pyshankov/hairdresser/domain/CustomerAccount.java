package com.pyshankov.hairdresser.domain;

import javax.persistence.Entity;

/**
 * Created by pyshankov on 16.10.2016.
 */

@Entity
public class CustomerAccount extends AbstractAccount {

    public CustomerAccount() {
    }

    public CustomerAccount( String fullName, String phone) {
        super(AccountType.CUSTOMER,fullName,phone);
    }

}

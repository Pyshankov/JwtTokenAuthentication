package com.pyshankov.hairdresser.domain;


/**
 * Created by pyshankov on 16.10.2016.
 */

public class CustomerAccount extends AbstractAccount {

    public CustomerAccount() {
    }

    public CustomerAccount( String fullName, String phone) {
        super(AccountType.CUSTOMER,fullName,phone);
    }

}

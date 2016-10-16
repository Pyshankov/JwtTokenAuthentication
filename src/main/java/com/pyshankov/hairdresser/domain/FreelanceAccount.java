package com.pyshankov.hairdresser.domain;

import javax.persistence.Entity;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Entity
public class FreelanceAccount extends  AbstractAccount{

    public FreelanceAccount() {
    }

    public FreelanceAccount( String fullName, String phone) {
        super(AccountType.FREELANCE,fullName,phone);
    }


}

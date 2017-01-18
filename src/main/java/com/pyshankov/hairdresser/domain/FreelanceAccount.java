package com.pyshankov.hairdresser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by pyshankov on 16.10.2016.
 */
public class FreelanceAccount extends  AbstractAccount{

    private List<String> servicesList;

    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
        this.servicesList = Objects.requireNonNull(servicesList);
    }

    public FreelanceAccount() {
        servicesList = new ArrayList<>();
    }

    public FreelanceAccount( String fullName, String phone) {
        super(AccountType.FREELANCE,fullName,phone);
        servicesList = new ArrayList<>();
    }


}

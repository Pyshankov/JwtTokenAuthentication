package com.pyshankov.hairdresser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Entity
public class FreelanceAccount extends  AbstractAccount{

    @JsonIgnore
    @Column
    private String servises;

    @Transient
    private List<String> servicesList;

    public String getServises() {
        return servises;
    }

    public void setServises(String servises) {
        this.servises = servises;
    }

    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
        this.servicesList = servicesList;
    }

    public FreelanceAccount() {
        servicesList = new ArrayList<>();
        servises="";
    }

    public FreelanceAccount( String fullName, String phone) {
        super(AccountType.FREELANCE,fullName,phone);
        servicesList = new ArrayList<>();
        servises="";
    }

    @PostLoad
    private void postLoad(){
        servicesList = Arrays.asList(servises.split(","));

    }

    @PrePersist
    private void prePersist(){
        servises = StringUtils.join(servicesList, ',');
    }


}

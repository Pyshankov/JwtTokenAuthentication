package com.pyshankov.hairdresser.domain;

import javax.persistence.*;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractAccount implements Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    protected AccountType accountType;

    @Column(name = "full_name", nullable = false)
    protected String fullName;

    @Column(name = "phone", nullable = false)
    protected String phone;

    public AbstractAccount(){
    }

    public AbstractAccount(AccountType accountType, String fullName, String phone) {
        this.accountType = accountType;
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

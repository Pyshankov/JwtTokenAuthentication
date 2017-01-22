package com.pyshankov.hairdresser.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by pyshankov on 11.10.2016.
 */
@Document(collection = User.COLLECTION_NAME)
public class User implements java.io.Serializable {

    public final static String COLLECTION_NAME = "user";

    @Id
    private Long id;

//    @Column(name = "username", unique = true, nullable = false, length = 20)
    @Indexed
    private String userName;

//    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

//    @Column(name = "email" ,unique = true, nullable = true)
    @Indexed
    protected String email;

//    @Column(name = "role", nullable = false, length = 10)
//    @Enumerated(EnumType.STRING)
    private Role role;

//    @Column(name = "isActivated",nullable = false)
    private boolean isActivated;
    //@JsonIgnore
    private Account account;

    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
        id=-1L;
        role= Role.USER;
        isActivated=false;
    }


    public User(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public enum Role{
        ADMIN,
        USER
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isActivated=" + isActivated +
                '}';
    }
}
package com.pyshankov.hairdresser.service;

import com.pyshankov.hairdresser.domain.*;

import java.util.List;

/**
 * Created by pyshankov on 1/18/17.
 */
public interface UserService {

    User save(User user);

    void update(User user);

    User findByUserName( String userName);

    void createAccountForUser(String userName,Account account);

    List<Account> findNearestAccountInRange(Location location, AccountType type, double km);
}

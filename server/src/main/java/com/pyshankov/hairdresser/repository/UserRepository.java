package com.pyshankov.hairdresser.repository;


import com.pyshankov.hairdresser.domain.Account;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.dto.AccountType;
import com.pyshankov.hairdresser.dto.ResponseAccountsDto;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by pyshankov on 11.10.2016.
 */

public interface UserRepository  {

    @PreAuthorize("hasRole('ROLE_USER')")
    User save(User user);

    @PreAuthorize("hasRole('ROLE_USER')")
    void update(User user);

    @PreAuthorize("hasRole('ROLE_USER')")
    User findOne(Long id);

    @PreAuthorize("hasRole('ROLE_USER')")
    default Account findAccountByUserName(String userName){
        return findByUserName(userName).getAccount();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    User findByUserName(@Param("name") String userName);

    @PreAuthorize("hasRole('ROLE_USER')")
    User findByEmail(@Param("email") String email);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll();

    @PreAuthorize("hasRole('ROLE_USER')")
    List<Account> findAccountsByAccountType(AccountType type);

    @PreAuthorize("hasRole('ROLE_USER')")
    List<Account> findAccountsByAccountType(AccountType type, Predicate<Account> filterFunction);

    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseAccountsDto findAccountsByAccountType(AccountType type, Predicate<Account> filterFunction, int offset, int limit);


}
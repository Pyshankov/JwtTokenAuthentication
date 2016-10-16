package com.pyshankov.hairdresser.repository;

import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Repository
public interface AccountRepository extends CrudRepository<AbstractAccount,Long> {
    Iterable<AbstractAccount> findByAccountType(AccountType accountType);
}

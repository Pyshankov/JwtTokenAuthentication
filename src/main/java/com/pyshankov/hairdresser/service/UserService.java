package com.pyshankov.hairdresser.service;

import com.pyshankov.hairdresser.domain.User;
import org.springframework.data.repository.query.Param;

/**
 * Created by pyshankov on 1/18/17.
 */
public interface UserService {

    User save(User user);

    User findByUserName( String userName);
}

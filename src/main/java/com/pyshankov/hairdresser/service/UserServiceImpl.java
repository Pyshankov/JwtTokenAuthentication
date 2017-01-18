package com.pyshankov.hairdresser.service;

import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.exception.UserConstraintException;
import com.pyshankov.hairdresser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pyshankov on 1/18/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        //TODO : add javax.validation check
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new UserConstraintException("Email already exist");
        }
        if(userRepository.findByUserName(user.getUserName())!=null){
            throw new UserConstraintException("user name already exist");
        }
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}

package com.pyshankov.hairdresser.service;

import com.pyshankov.hairdresser.domain.*;
import com.pyshankov.hairdresser.dto.AccountType;
import com.pyshankov.hairdresser.dto.Location;
import com.pyshankov.hairdresser.dto.ResponseAccountsDto;
import com.pyshankov.hairdresser.exception.UserConstraintException;
import com.pyshankov.hairdresser.exception.ValidationException;
import com.pyshankov.hairdresser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void createAccountForUser(String userName,Account account){
        User u = userRepository.findByUserName(userName);
        if (u==null){
            throw new UserConstraintException(userName+" does not exist");
        }
        if(u.getAccount()!=null) {
            throw new UserConstraintException("Account for: "+userName+" already created");
        }
        u.setAccount(account);
        userRepository.update(u);
    }

    @Override
    public void dropAccountForUser(String userName){
        User u = userRepository.findByUserName(userName);
        if (u==null){
            throw new UserConstraintException(userName+" does not exist");
        }
        if(u.getAccount()!=null) {
            u.setAccount(null);
            userRepository.update(u);
        }
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<Account> findNearestAccountInRange(Location location, AccountType type, double km) {
        return userRepository
                .findAccountsByAccountType(type,(account)->  DistanceEvaluatorService.length(location,account.getLocation()) < km);
    }

    @Override
    public ResponseAccountsDto findNearestAccountInRange(Location location, AccountType type, double km, int offset, int limit){
        return userRepository
                .findAccountsByAccountType(type,(account)->  DistanceEvaluatorService.length(location,account.getLocation()) < km,
                        offset,
                        limit);

    }

}

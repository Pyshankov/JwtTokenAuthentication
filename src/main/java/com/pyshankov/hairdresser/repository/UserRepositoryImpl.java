package com.pyshankov.hairdresser.repository;

import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.AccountType;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.repository.sequence.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by pyshankov on 1/18/17.
 */
@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public User save(User user){
        user.setId(sequenceDao.getNextSequenceId(User.COLLECTION_NAME));
        mongoOperations.save(user);
     return user;
    }

    @Override
    public void update(User user){
        mongoOperations.save(user);
    }

    @Override
    public User findOne(Long id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public User findByUserName(@Param("name") String userName) {
        return mongoOperations.findOne(Query.query(Criteria.where("userName").is(userName)), User.class);
    }

    @Override
    public User findByEmail(@Param("email") String email) {
        return mongoOperations.findOne(Query.query(Criteria.where("email").is(email)), User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoOperations.findAll(User.class);
    }

    @Override
    public List<AbstractAccount> findAccountsByAccountType(AccountType type) {
        return findAccountsByAccountType(type, (account)->true);
    }

    @Override
    public List<AbstractAccount> findAccountsByAccountType(AccountType type, Predicate<AbstractAccount> filterFunction) {
        return mongoOperations.find(Query.query(Criteria.where("account.accountType").is(type.toString())),User.class).stream().map(User::getAccount).filter(filterFunction).collect(Collectors.toList());
    }

}

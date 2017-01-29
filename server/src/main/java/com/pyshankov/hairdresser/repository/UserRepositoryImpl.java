package com.pyshankov.hairdresser.repository;

import com.pyshankov.hairdresser.domain.Account;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.dto.AccountDto;
import com.pyshankov.hairdresser.dto.AccountType;
import com.pyshankov.hairdresser.dto.FromDto;
import com.pyshankov.hairdresser.dto.ResponseAccountsDto;
import com.pyshankov.hairdresser.repository.sequence.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;
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
    public List<Account> findAccountsByAccountType(AccountType type) {
        return findAccountsByAccountType(type, (account)->true);
    }

    @Override
    public List<Account> findAccountsByAccountType(AccountType type, Predicate<Account> filterFunction) {
        return mongoOperations
                .find(Query.query(Criteria.where("account.accountType").is(type.toString())),User.class)
                .stream()
                .map(User::getAccount)
                .filter(filterFunction)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseAccountsDto findAccountsByAccountType(AccountType type, Predicate<Account> filterFunction , int offset, int limit) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account.accountType").is(type.toString()));
        long count = mongoOperations.find(query,User.class)
                .stream()
                .map(User::getAccount)
                .filter(filterFunction)
                .count();

        if(limit==0) {
            limit = (int) count;
        }
//        query.skip(offset);
//        query.limit(limit);

        List<AccountDto> result = mongoOperations
                .find(query,User.class)
                .stream()
                .map(u-> FromDto.toCreateAccountDto(u.getAccount()))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        return new ResponseAccountsDto(result,count,offset,result.size());
    }


}

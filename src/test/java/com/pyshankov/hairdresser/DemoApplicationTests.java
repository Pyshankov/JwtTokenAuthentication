package com.pyshankov.hairdresser;

import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.AccountType;
import com.pyshankov.hairdresser.domain.CustomerAccount;
import com.pyshankov.hairdresser.domain.FreelanceAccount;
import com.pyshankov.hairdresser.domain.Location;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.repository.UserRepository;
import com.pyshankov.hairdresser.repository.sequence.SequenceDao;
import com.pyshankov.hairdresser.service.DistanceEvaluatorService;
import com.pyshankov.hairdresser.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	SequenceDao sequenceDao;
	@Autowired
	UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;


	@After
	public void after(){
		mongoTemplate.getDb().dropDatabase();
	}

	@Before
	public void before(){
		mongoTemplate.getDb().dropDatabase();

		sequenceDao.insertZeroVal(User.COLLECTION_NAME);
		User me = DemoApplication.DataGenerator.createCustomerUser("pyshankov","Pavel Andreevich");
		User me2 = DemoApplication.DataGenerator.createCustomerUser("vlad","Vladislav Victorovich");
		userService.save(me);
		userService.save(me2);

		for (int i=0; i < 100 ; i++){
			userService.save(DemoApplication.DataGenerator.generateNextFreelanceUser());
		}


	}

	@Test
	public void yest(){
		User u1 = userService.findByUserName("pyshankov");
		User u2 = userService.findByUserName("vlad");
		System.out.println(DistanceEvaluatorService.length(u1.getAccount().getLocation(),u2.getAccount().getLocation()));

		System.out.println(userService.findNearestAccountInRange(new Location(50.4652748, 30.4620194), AccountType.FREELANCE,10));
		System.out.println(userService.findNearestAccountInRange(new Location(50.4652748, 30.4620194), AccountType.FREELANCE,10).size());
	}

	@Test
	public void yest2(){
		userService.findNearestAccountInRange(new Location(50, 30), AccountType.FREELANCE,100,10,10);
	}


}

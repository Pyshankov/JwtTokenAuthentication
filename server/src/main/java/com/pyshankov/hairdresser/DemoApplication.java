package com.pyshankov.hairdresser;


import com.pyshankov.hairdresser.domain.AbstractAccount;
import com.pyshankov.hairdresser.domain.CustomerAccount;
import com.pyshankov.hairdresser.domain.FreelanceAccount;
import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.dto.Location;
import com.pyshankov.hairdresser.repository.sequence.SequenceDao;
import com.pyshankov.hairdresser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(UserService userService,SequenceDao sequenceDao) {
		return (args) -> {
			sequenceDao.insertZeroVal(User.COLLECTION_NAME);
			User me = DataGenerator.createCustomerUser("pyshankov","Pavel Andreevich");
			User me2 = DataGenerator.createCustomerUser("vlad","Vladislav Victorovich");
			userService.save(me);
			userService.save(me2);

			for (int i=0; i < 100 ; i++){
				userService.save(DataGenerator.generateNextFreelanceUser());
			}
		};
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@PreDestroy
	public void deleteDb(){
		mongoTemplate.getDb().dropDatabase();
	}




	static class DataGenerator{

		private static Random random = new Random();

		private static int counter = 0;

		private static List<String> userNames(){
			List<String> names = new ArrayList<>();
			names.add("Pavel");
			names.add("Andrii");
			names.add("Valerii");
			names.add("Alexandr");
			names.add("Vitalii");
			names.add("Vladimir");
			names.add("Artur");
			names.add("Arsen");
			names.add("Urii");
			names.add("Taras");
			return names;
		};

		private static  List<String> sureNames(){
			List<String> list = new ArrayList<>();
			list.add("Odintsov");
			list.add("Zavatslii");
			list.add("Artemenkov");
			list.add("Zatsepin");
			list.add("Vlasenko");
			list.add("Ivanov");
			list.add("Petrov");
			list.add("Tolstoi");
			list.add("Pushkin");
			list.add("Esenin");
			return list;
		};

		private static List<String> serviceList(){
			List<String> list = new ArrayList<>();
			list.add("Маникюр");
			list.add("Массаж");
			list.add("Стрижка");
			list.add("Педикюр");
			return list;
		}

		private static Location getNextLocation(){
			return new Location(getRandBetween(49,51),getRandBetween(29,31));
		}

		private static double getRandBetween(double min, double max){
			return min + (max - min) * random.nextDouble();
		}

		public static User generateNextFreelanceUser(){
			String fullName = userNames().get(((random.nextInt()% 10)+10)%10 ) + " " + sureNames().get(((random.nextInt()% 10)+10)%10 );
			String userName = "test"+counter++;
			String userMail = userName+"@mail.com";
			String userPassword = userName;

			FreelanceAccount freelanceAccount = new FreelanceAccount(fullName,Long.toString(Math.abs(random.nextLong())));
			freelanceAccount.getServicesList().addAll(serviceList());
			freelanceAccount.setLocation(getNextLocation());

			User u = new User();
			u.setUserName(userName);
			u.setPassword(userPassword);
			u.setActivated(true);
			u.setEmail(userMail);


			u.setAccount(freelanceAccount);
			return u;
		}

		public static User createCustomerUser(String userName,String fullName){
			User u1 = new User(userName, "123321");
			u1.setEmail(userName+"@gmail.com");
			u1.setActivated(true);
			u1.setRole(User.Role.ADMIN);
			AbstractAccount u1Account = new CustomerAccount(fullName, "0975555156");
			u1Account.setLocation(getNextLocation());
			u1.setAccount(u1Account);
			return u1;
		}

	}

}

package com.pyshankov.hairdresser;

import com.pyshankov.hairdresser.domain.*;
import com.pyshankov.hairdresser.repository.AccountRepository;
import com.pyshankov.hairdresser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(UserRepository repository, AccountRepository accountRepository) {
		return (args) -> {
			User u1 = new User("pyshankov","123321");
			u1.setActivated(true);
			u1.setRole(User.Role.ADMIN);
			User u2 = new User("vova","123");
			u2.setActivated(true);
			User u3 = new User("vlad","111111");
			u2.setActivated(true);

			AbstractAccount u1Account = new CustomerAccount("Pavel Andreevich","0975555156");
			u1Account.setLocation(new Location("50.4652748","30.4620194"));

			FreelanceAccount u2Account = new FreelanceAccount("Vladimir Pavlovich","0973453123");
			u2Account.setLocation(new Location("50.4653748","30.4610194"));
			u2Account.getServicesList().add("Стрижка");
			u2Account.getServicesList().add("Маникюр");
			u2Account.getServicesList().add("Массаж");

			FreelanceAccount u3Account = new FreelanceAccount("Vladimir Sergeevich","0972222123");
			u3Account.getServicesList().add("Маникюр");
			u3Account.getServicesList().add("Массаж");
			u3Account.setLocation(new Location("50.4655748","30.4627194"));

			u1Account = accountRepository.save(u1Account);
			u2Account = accountRepository.save(u2Account);
			u2Account = accountRepository.save(u3Account);

			u1.setAccount(u1Account.getId());
			u2.setAccount(u2Account.getId());
			u3.setAccount(u3Account.getId());

			u1=repository.save(u1);
			u2=repository.save(u2);
			u3=repository.save(u3);

			System.out.println(u1);
			System.out.println(u2);
			System.out.println(u3);
		};
	}


}

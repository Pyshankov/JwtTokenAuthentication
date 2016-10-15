package com.pyshankov.hairdresser;

import com.pyshankov.hairdresser.domain.User;
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
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			User u1 = new User("pyshankov","123321");
			u1.setActivated(true);
			u1.setRole(User.Role.ADMIN);
			User u2 = new User("vova","123");
			u2.setActivated(true);
			User u3 = new User("alex","123");

			u1=repository.save(u1);
			u2=repository.save(u2);
			u3=repository.save(u3);



			System.out.println(u1);
			System.out.println(u2);
			System.out.println(u3);




		};
	}


}

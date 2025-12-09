package hu.cubixwebshop.userservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubixwebshop.tokenlib.JwtAuthFilter;

@SpringBootApplication(scanBasePackageClasses = {UserServiceApplication.class, JwtAuthFilter.class})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

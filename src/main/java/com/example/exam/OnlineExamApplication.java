package com.example.exam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.exam.model.User;
import com.example.exam.repository.UserRepository;

@SpringBootApplication
public class OnlineExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}


	//  will run once when the application starts
	@Bean
	CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			String adminUsername = "admin";

			// Check if the admin user already exists
			if (userRepository.findByUsername(adminUsername).isEmpty()) {
				User adminUser = new User();
				adminUser.setUsername(adminUsername);
				// Make sure to use the password encoder
				adminUser.setPassword(passwordEncoder.encode("admin"));
				adminUser.setRole("ROLE_ADMIN");
				adminUser.setFullName("Admin");

				userRepository.save(adminUser);
				System.out.println(">>> Admin user created: admin / admin <<<");
			} else {
				System.out.println(">>> Admin user 'admin' already exists. <<<");
			}
		};
	}
}

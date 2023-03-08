package edu.maszek.brainpowerquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BrainpowerquizApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainpowerquizApplication.class, args);
	}

}

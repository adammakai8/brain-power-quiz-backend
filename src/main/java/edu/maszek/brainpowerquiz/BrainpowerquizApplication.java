package edu.maszek.brainpowerquiz;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories
public class BrainpowerquizApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainpowerquizApplication.class, args);
	}

}

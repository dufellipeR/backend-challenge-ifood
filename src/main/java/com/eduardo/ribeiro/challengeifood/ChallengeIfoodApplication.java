package com.eduardo.ribeiro.challengeifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ChallengeIfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeIfoodApplication.class, args);
    }

}

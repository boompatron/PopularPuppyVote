package com.numble.popularpuppyvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PopularPuppyVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopularPuppyVoteApplication.class, args);
	}

}

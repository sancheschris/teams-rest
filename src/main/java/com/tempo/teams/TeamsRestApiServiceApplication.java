package com.tempo.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tempo.teams"})
public class TeamsRestApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsRestApiServiceApplication.class, args);
	}

}

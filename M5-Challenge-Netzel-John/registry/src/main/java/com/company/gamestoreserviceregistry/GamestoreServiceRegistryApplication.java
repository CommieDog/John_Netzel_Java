package com.company.gamestoreserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GamestoreServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreServiceRegistryApplication.class, args);
	}

}

package com.sprint.pet_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//  The main entry point for the HappyPaws Pet Shop Management System.
//  Starts up the Spring Boot application context and bootstraps the backend services.

@SpringBootApplication
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

}

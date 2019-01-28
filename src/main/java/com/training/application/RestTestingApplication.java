package com.training.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.training.rest.controller.PersonController;

@SpringBootApplication
public class RestTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonController.class, args);
	}

}


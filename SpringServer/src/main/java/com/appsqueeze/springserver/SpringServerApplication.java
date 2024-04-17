package com.appsqueeze.springserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.appsqueeze.springserver")
public class SpringServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringServerApplication.class, args);
	}

}

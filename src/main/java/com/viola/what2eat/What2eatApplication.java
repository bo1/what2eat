package com.viola.what2eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class What2eatApplication {

	public static void main(String[] args) {
		SpringApplication.run(What2eatApplication.class, args);
	}
}

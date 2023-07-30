package com.example.creatorconnectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorconnectBackendApplication {

    // The main method which serves as the entry point for the application
	public static void main(String[] args) {
	    // SpringApplication.run method starts the embedded Tomcat server (by default) and initializes Spring's ApplicationContext.
		SpringApplication.run(CreatorconnectBackendApplication.class, args);
	}

}

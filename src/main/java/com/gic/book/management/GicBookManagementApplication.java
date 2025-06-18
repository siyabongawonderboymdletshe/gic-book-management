package com.gic.book.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main application class for the Gic Book Management application.
 * It uses Spring Boot to bootstrap the application.
 */
@SpringBootApplication
public class GicBookManagementApplication {

	/**
	 * The main method serves as the entry point for the Spring Boot application.
	 * It starts the application by invoking SpringApplication.run().
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(GicBookManagementApplication.class, args);
	}
}

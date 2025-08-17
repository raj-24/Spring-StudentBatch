package com.example.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentBatchApplication.class, args);
		System.out.println("Application is Running Successfully......");
	}

}

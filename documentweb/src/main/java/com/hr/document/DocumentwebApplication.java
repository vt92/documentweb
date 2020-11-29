package com.hr.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DocumentwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentwebApplication.class, args);
	}

}

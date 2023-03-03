package com.example.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNoteApplication.class, args);
	}

}

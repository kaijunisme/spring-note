package com.example.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class SpringBootNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNoteApplication.class, args);
	}

}

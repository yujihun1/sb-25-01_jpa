package com.ll.sb_25_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sb2501Application {

	public static void main(String[] args) {
		SpringApplication.run(Sb2501Application.class, args);
	}

}

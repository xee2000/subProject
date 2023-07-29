package com.ljh.exam.TwoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = "com.ljh.exam.TwoProject.entity")
public class TwoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoProjectApplication.class, args);
	}

}

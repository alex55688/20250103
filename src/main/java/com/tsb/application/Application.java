package com.tsb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.tsb.entity"})
@EnableJpaRepositories(basePackages = {"com.tsb.entity"})
@ComponentScan(basePackages = {"com.tsb.controller", "com.tsb.filter", "com.tsb.component", "com.tsb.service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

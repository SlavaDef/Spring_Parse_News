package com.diplom.spring_news_agregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringNewsAgregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNewsAgregatorApplication.class, args);
	}

}

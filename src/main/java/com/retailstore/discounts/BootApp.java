package com.retailstore.discounts;

import com.retailstore.discounts.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BootApp {

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}

}

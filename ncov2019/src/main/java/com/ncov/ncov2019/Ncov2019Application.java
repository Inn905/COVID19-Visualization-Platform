package com.ncov.ncov2019;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration

public class Ncov2019Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Ncov2019Application.class, args);
	}

	protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(Ncov2019Application.class);
	}
}

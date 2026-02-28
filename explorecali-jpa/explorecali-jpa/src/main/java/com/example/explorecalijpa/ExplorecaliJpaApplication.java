package com.example.explorecalijpa;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExplorecaliJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliJpaApplication.class, args);
	}

	@Bean
	public OpenAPI swaggerHeader(){
		return new OpenAPI()
				.info((new Info())
				.description("Serv8ices for the Explore California Relational Database.")
				.title(StringUtils.substringBefore(getClass().getSimpleName(), "$"))
				.version("3.0.0")
				);
	}
}

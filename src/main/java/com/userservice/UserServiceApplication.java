package com.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Bean
	// If multiple services are running and connected to service registry then it will load balance your request, Here no need to mention 
	// host and port just give the Service Application Name as http://DEPARTMENT-SERVICE/departments
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

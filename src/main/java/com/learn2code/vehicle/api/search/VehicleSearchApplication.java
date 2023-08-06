package com.learn2code.vehicle.api.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VehicleSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSearchApplication.class, args);
	}

	@Bean
	public RestTemplate getTemplate()
	{
		return new RestTemplate();
	}

}

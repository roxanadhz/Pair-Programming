package com.trilogyed.vinlookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VinLookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinLookupApplication.class, args);
	}

}

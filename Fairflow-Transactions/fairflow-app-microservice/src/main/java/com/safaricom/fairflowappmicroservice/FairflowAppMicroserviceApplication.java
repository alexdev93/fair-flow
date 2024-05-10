package com.safaricom.fairflowappmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FairflowAppMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairflowAppMicroserviceApplication.class, args);
	}

}

package com.example.ecommerceSpringOrderService;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommerceSpringOrderServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load() ;

		dotenv.entries().forEach(dotenvEntry -> {
			System.setProperty(dotenvEntry.getKey() , dotenvEntry.getValue()) ;
		});

		SpringApplication.run(EcommerceSpringOrderServiceApplication.class, args);
	}

}

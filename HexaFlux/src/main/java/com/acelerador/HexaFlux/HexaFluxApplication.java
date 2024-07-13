package com.acelerador.HexaFlux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HexaFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaFluxApplication.class, args);
	}

}

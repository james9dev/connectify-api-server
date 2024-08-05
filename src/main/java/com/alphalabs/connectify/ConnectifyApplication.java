package com.alphalabs.connectify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ConnectifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectifyApplication.class, args);
	}

}

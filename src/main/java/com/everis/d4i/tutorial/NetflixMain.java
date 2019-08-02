package com.everis.d4i.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NetflixMain {

	public static void main(final String[] args) {
		SpringApplication.run(NetflixMain.class, args);
	}

}
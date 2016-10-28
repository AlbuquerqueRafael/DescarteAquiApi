package com.descarteaqui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
	
@Configuration
@ComponentScan("com.descarteaqui")
@EnableAutoConfiguration
public class DescarteAquiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DescarteAquiApiApplication.class, args);
	}
}

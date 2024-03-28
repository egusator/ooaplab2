package com.example.ooaplab2;

import com.example.ooaplab2.config.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperty.class)
public class Ooaplab2Application {

	public static void main(String[] args) {

		SpringApplication.run(Ooaplab2Application.class, args);
	}

}

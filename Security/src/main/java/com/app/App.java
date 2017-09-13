package com.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@EnableAutoConfiguration
@SpringBootApplication
@EnableOAuth2Sso
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}

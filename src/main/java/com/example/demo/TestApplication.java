package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestApplication {
	@Value("${my.var.message}")
	private String message;

	@GetMapping("/")
	public String helloWorld() {
		return this.message;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		System.out.println("server running");
	}

}

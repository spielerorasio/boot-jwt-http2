package com.orasio.example.boot.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BootJwtHttp2Application {

	@RequestMapping("/")
	String ping() {
		return "I'm alive";
	}

	public static void main(String[] args) {
		SpringApplication.run(BootJwtHttp2Application.class, args);
	}
}

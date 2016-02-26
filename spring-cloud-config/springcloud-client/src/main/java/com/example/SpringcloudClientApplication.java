package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@RestController

// Refresh scope zorgt voor verversing van de value, initieren met POST naar http//.../refresh
@RefreshScope
public class SpringcloudClientApplication {

	@Value("${info.foo}")
	private String testwaarde;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return String.format("[%s]\n", testwaarde);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudClientApplication.class, args);
	}
}

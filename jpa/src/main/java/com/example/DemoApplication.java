package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private TypeARepository typeARepository;

	@Autowired
	private TypeBRepository typeBRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public String test() {

		typeARepository.save(new TypeA(1L, 5L, "Naam A"));
		typeBRepository.save(new TypeB(10L, "Naam B"));

		return "";
	}
}

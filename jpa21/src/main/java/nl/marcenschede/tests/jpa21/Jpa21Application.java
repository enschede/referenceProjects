package nl.marcenschede.tests.jpa21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Jpa21Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpa21Application.class, args);
	}
}

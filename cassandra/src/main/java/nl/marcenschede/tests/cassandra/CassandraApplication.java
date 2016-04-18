package nl.marcenschede.tests.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CassandraApplication {

	static final Logger logger = LoggerFactory.getLogger(CassandraApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {

		return "OK";
	}


}

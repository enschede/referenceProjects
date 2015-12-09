package nl.marcenschede.springtest.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@ComponentScan("nl.marcenschede.springtest")
@ImportResource("classpath:application-*.xml")
@EnableJpaRepositories                                // Create JPA Repositories
@Import(RepositoryRestMvcAutoConfiguration.class)     // Expose REST API automatically
@EnableBatchProcessing
@PropertySource("classpath:batch.properties")
public class App {

    private static Class app = App.class;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(app, args);
    }

}

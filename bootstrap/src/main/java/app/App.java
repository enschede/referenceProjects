package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath:application-*.xml")
@EnableJpaRepositories                                // Create JPA Repositories
@Import(RepositoryRestMvcAutoConfiguration.class)     // Expose REST API automatically
public class App extends SpringBootServletInitializer {

    private static Class app = App.class;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(app, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(app);
    }

}

package nl.marcenschede.tests.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableWebMvc			// Enabling this will block fixed content
@EnableSwagger2
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

	// Bij het gebruik van Swagger moet ook de SwaggerUI front-end toegevoegd worden, zie src/main/resources/static
	// In de index.html moet de url wijzen naar de swagger api (meestal /v2/api-docs)

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("greetings")
				.apiInfo(apiInfo())
				.select()
//				.paths(regex("/greeting.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring REST Sample with Swagger")
				.description("Spring REST Sample with Swagger")
//				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Marc Enschede")
//				.license("Apache License Version 2.0")
//				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
//				.version("2.0")
				.build();
	}
}

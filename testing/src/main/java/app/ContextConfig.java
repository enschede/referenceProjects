package app;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.logging.Logger;

/**
 * Created by marc on 05/09/15.
 */
@Configuration
public class ContextConfig {

    @Configuration
//    @Profile("!integrationTest")      
    @Profile("default")                 // Dit profile bestaat als geen ander profile aanwezig is
    public static class IntegrationTestContextConfig {

        @Bean(name = "smsClient")
        public HttpClient getSmsHttpClient() {
            return HttpClientBuilder.create().build();
        }
    }
}

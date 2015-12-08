package MockitoRestIntegrationTest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by marc on 05/09/15.
 */
@Configuration
@Profile("integrationTest")
public class IntegrationTestConfiguration {

    @Bean(name = "smsClient")
    public HttpClient getSmsClient() {
        return Mockito.mock(HttpClient.class);
    }
}

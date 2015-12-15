package app;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = App.class)
@RestController
public class App {

    private static Class app = App.class;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(app, args);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String executeTest() throws IOException, URISyntaxException {

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final URL url = new URL("http://localhost:8091/test");
        final HttpGet httpGet = new HttpGet(url.toURI());

        final CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return "";
        }

        java.util.Scanner s = new java.util.Scanner(response.getEntity().getContent()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}

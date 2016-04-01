package app;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MinimalTest {

    final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Rule
    public WireMockRule wireMockRule =
            new WireMockRule(WireMockConfiguration.wireMockConfig());

    @Test
    public void shouldExecuteRestRequest() throws Exception {
        WireMock.stubFor(WireMock
                .get(WireMock.urlEqualTo("/test"))
                .willReturn(WireMock.aResponse()
                        .withBody("Hallo Testert!").withStatus(200)));

        CloseableHttpResponse response = get("http://localhost:8080/test");
        assertThat(IOUtils.toString(response.getEntity().getContent()),
                is("Hallo Testert!"));
    }

    private CloseableHttpResponse get(String url) throws IOException, URISyntaxException {
        return httpClient.execute(new HttpGet(new URL(url).toURI()));
    }

}

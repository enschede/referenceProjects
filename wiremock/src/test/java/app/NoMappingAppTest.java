package app;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = App.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class NoMappingAppTest {

    private WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8091));

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private App app;

    @Test
    public void shouldReplyOnMvc() throws Exception {

        // Given
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        wireMockServer.start();;
        WireMock.configureFor(8091);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/test"))
                .willReturn(WireMock.aResponse().withBody("Hello Testert!").withStatus(200)));

        // When
        MvcResult mvcResult =
                mockMvc.perform(get("/test").contentType(MediaType.TEXT_PLAIN))
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(), Matchers.is("Hello Testert!"));
        assertThat(mvcResult.getResponse().getStatus(), Matchers.is(200));
    }


}

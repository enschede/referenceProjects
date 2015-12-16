package app;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.Matchers;
import org.junit.Rule;
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

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = App.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private App app;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8091));

    @Test
    public void shouldReplyOnMvc() throws Exception {

        // Given
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // When
        MvcResult mvcResult =
                mockMvc.perform(get("/test").contentType(MediaType.TEXT_PLAIN))
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(), Matchers.is("Hello Testert!"));

    }

}
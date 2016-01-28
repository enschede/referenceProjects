package SpringMvcIntegrationTest;

import app.App;
import app.domain.Gebied;
import app.domain.VergunningAanvraag;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by marc on 21/05/15.
 */
@ContextConfiguration(classes = {App.class,TestConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringRunnerMvcIntegrationTest {

    // De WebAppConfiguration annotation zorgt dat deze gevuld wordt
    @Autowired
    private WebApplicationContext wac;

    @Test
    public void shouldReplyOnMvc() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(2);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(vergunningAanvraag);

        // When
        MvcResult mvcResult =
                mockMvc.perform(post("/verwerkvergunnningaanvraag").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // Then
        String result = mvcResult.getResponse().getContentAsString();

//        VergunningAanvraag vergunningAanvraag1 = mapper.readValue(result, VergunningAanvraag.class);

        assertThat(result, is(""));
    }
    
}

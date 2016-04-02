package nl.marcenschede.tests.swagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SwaggerApplication.class)
@WebAppConfiguration
public class SwaggerApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void shouldFindSwaggerInformation() throws Exception {

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		mockMvc.perform(get("/v2/api-docs")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}

}

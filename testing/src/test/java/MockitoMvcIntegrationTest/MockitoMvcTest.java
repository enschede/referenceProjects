package MockitoMvcIntegrationTest;

import app.presentation.Controller;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller via Mvc aangeroepen vanuit Mockito
 */
public class MockitoMvcTest {

    @InjectMocks
    private Controller controller;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSuccesfulGet()
            throws Exception {
        mockMvc.perform(get("/greeting")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetResult()
            throws Exception {

        // When
        MockHttpServletResponse response =
                mockMvc.perform(get("/greeting")).andReturn().getResponse();

        // Then
        Assert.assertThat(
                response.getContentAsString(),
                CoreMatchers.equalTo("{\"id\":10,\"content\":\"Hallo!\"}"));

    }


}

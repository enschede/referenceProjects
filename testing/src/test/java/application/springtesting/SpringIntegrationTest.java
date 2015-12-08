package application.springtesting;

import app.application.BrievenProcessor;
import app.application.VerwerkVergunningProcessor;
import app.domain.Gebied;
import app.domain.VergunningAanvraag;
import app.domain.VergunningPolicy;
import app.presentation.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Deze situatie gebruiken wanneer een Spring component, een paar lagen diep, gemockt moet worden
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringIntegrationTest.SpringControllerConfiguration.class})
public class SpringIntegrationTest {

    @Autowired
    private Controller controller;

    @Test
    public void processTest() {
        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(2);

        String result = controller.verwerkVergunningAanvraag(vergunningAanvraag);

        assertThat(result, isEmptyString());
    }

    @Configuration
    public static class SpringControllerConfiguration {
        @Mock
        private BrievenProcessor brievenProcessor;
        @Mock
        VergunningPolicy vergunningPolicy;
        
        public SpringControllerConfiguration() {
            initMocks(this);
            
            when(vergunningPolicy.isToegestaan(any(Gebied.class), any(Integer.class))).thenReturn(true);
        }

        @Bean
        public VergunningPolicy vergunningPolicy() {
            return vergunningPolicy;
        }

        @Bean
        public Controller controller() {
            Controller controller = new Controller(verwerkVergunningProcessor());
            return controller;
        }

        @Bean
        public VerwerkVergunningProcessor verwerkVergunningProcessor() {
            return new VerwerkVergunningProcessor(vergunningPolicy(), brievenProcessor());
        }

        @Bean
        public BrievenProcessor brievenProcessor() {
            return brievenProcessor;
        }
    }


}

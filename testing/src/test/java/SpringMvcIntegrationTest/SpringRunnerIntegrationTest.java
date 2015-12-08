package SpringMvcIntegrationTest;

import app.App;
import app.domain.Gebied;
import app.domain.VergunningAanvraag;
import app.presentation.Controller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by marc on 21/05/15.
 */
@ContextConfiguration(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringRunnerIntegrationTest {
    
    @Autowired
    private Controller controller;
    
    @Test
    public void test01() {

        Assert.assertNotNull(controller);

        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(2);
        
        // When
        controller.verwerkVergunningAanvraag(vergunningAanvraag);
        
        // Then
    }
    
}

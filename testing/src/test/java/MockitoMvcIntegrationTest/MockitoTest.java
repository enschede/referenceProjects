package MockitoMvcIntegrationTest;

import app.application.VerwerkVergunningProcessor;
import app.domain.Gebied;
import app.domain.VergunningAanvraag;
import app.presentation.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Controller direct aangeroepen vanuit Mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    
    @InjectMocks
    private Controller controller;
    
    @Mock
    private VerwerkVergunningProcessor verwerkVergunningProcessor;
    
    @Test
    public void test01() {
        // Given
        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(2);
        
        // When
        controller.verwerkVergunningAanvraag(vergunningAanvraag);
        
        // Then
        Mockito.verify(verwerkVergunningProcessor, Mockito.times(1)).verwerk(Matchers.any(VergunningAanvraag.class));
    }
    
}

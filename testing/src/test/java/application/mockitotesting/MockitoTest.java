package application.mockitotesting;

import app.application.BrievenProcessor;
import app.application.VerwerkVergunningProcessor;
import app.domain.Gebied;
import app.domain.VergunningAanvraag;
import app.domain.VergunningPolicy;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Standaard mock testing
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    
    @InjectMocks
    private VerwerkVergunningProcessor verwerkVergunningProcessor;
    
    @Mock
    private BrievenProcessor brievenProcessor;
    
    @Mock
    private VergunningPolicy vergunningPolicy;

    @Test
    public void testVerlening() {
        // Given
        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(2);

        ArgumentCaptor<Gebied> gebiedArgumentCaptor = ArgumentCaptor.forClass(Gebied.class);
        when(vergunningPolicy.isToegestaan(gebiedArgumentCaptor.capture(), any(Integer.class))).thenReturn(true);

        // When
        verwerkVergunningProcessor.verwerk(vergunningAanvraag);

        // Then
        Mockito.verify(brievenProcessor, Mockito.times(1)).verzendVergunning(any(VergunningAanvraag.class));
        Assert.assertThat(gebiedArgumentCaptor.getValue(), Matchers.is(Gebied.BUITENGEBIED));
    }

    @Test
    public void testAfwijzing() {
        // Given
        VergunningAanvraag vergunningAanvraag = new VergunningAanvraag();
        vergunningAanvraag.setGebied(Gebied.BUITENGEBIED);
        vergunningAanvraag.setBouwhoogte(4);

        // When
        verwerkVergunningProcessor.verwerk(vergunningAanvraag);

        // Then
        Mockito.verify(brievenProcessor, Mockito.times(1)).verzendAfwijzingsbrief(any(Gebied.class), any(Integer.class));
    }
    
}

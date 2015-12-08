package application.mockitotesting;

import app.domain.Gebied;
import app.domain.VergunningPolicy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.not;

/**
 * Standaard mock testing
 */
@RunWith(MockitoJUnitRunner.class)
public class VergunningPolicyTest {
    
    @InjectMocks
    private VergunningPolicy vergunningPolicy;
    
    @Test
    public void kleinerDanTweeMeter() {
        Assert.assertFalse(vergunningPolicy.isToegestaan(Gebied.BUITENGEBIED, 1));
    }

    @Test
    public void centrumMaximaal() {
        Assert.assertTrue(vergunningPolicy.isToegestaan(Gebied.CENTRUM, 40));
    }

    @Test
    public void centrumTeHoog() {
        Assert.assertFalse(vergunningPolicy.isToegestaan(Gebied.CENTRUM, 41));
    }

}

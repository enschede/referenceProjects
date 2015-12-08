package basics.hamcrest;

import app.domain.Gebied;
import app.domain.VergunningPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static basics.hamcrest.MyTester.even;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by marc on 21/05/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class VergunningAanvraagTest {
    
    @InjectMocks
    private VergunningPolicy vergunningPolicy;
    
    @Test
    public void assertNotEqual() {
        assertThat(vergunningPolicy.isToegestaan(Gebied.CENTRUM, 41), not(equalTo(true)));
    }

    @Test
    public void assertClass() {
        assertThat(vergunningPolicy.isToegestaan(Gebied.CENTRUM, 41), any(Boolean.class));
        assertThat(vergunningPolicy.isToegestaan(Gebied.CENTRUM, 41), isA(Boolean.class));
    }

    @Test
    public void assertCloseTo() {
        // Valide range is van 2.00 tot 4.00 (3.00 +- 1.00)
        assertThat(BigDecimal.valueOf(325, 2), closeTo(BigDecimal.valueOf(300, 2), BigDecimal.valueOf(100, 2)));
    }

    @Test
    public void myOwnTesterOk() {
        assertThat(6, even());
    }

    @Test(expected = AssertionError.class)
    public void myOwnTesterNotOk() {
        assertThat(7, even());
    }

    @Test
    public void myOwnTesterInverted() {
        assertThat("Getallentester", 7, not(even()));
    }

    /**
     * Grapje; deze is altijd waar door de of-constructie
     */
    @Test
    public void altijdWaar() {
        assertThat("Altijd waar", 6, anyOf(even(), not(even())));
    }

    @Test
    public void collectionTest() {
        assertThat(Arrays.asList("foo", "bar"), hasItem("bar"));
    }
    
}

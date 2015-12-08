package app;

import nl.marcenschede.dozerdemo.melding.v1_0.CreditRequestAvailable;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by marc on 08/12/15.
 */
public class RootFullXmlHandlingTest {

    @Test
    public void testMashalObjectToString() throws Exception {

        // Given
        InputStream xmlStream = this.getClass().getResourceAsStream("creditRequestAvailable.xml");
        CreditRequestAvailable creditRequestAvailable =
                (CreditRequestAvailable) RootFullXmlHandling.unmarshalStreamToObject(xmlStream, CreditRequestAvailable.class);

        // When
        String actualXml = RootFullXmlHandling.mashalObjectToString(creditRequestAvailable);

        // Then
        assertThat(actualXml, CoreMatchers.containsString("creditRequestAvailable"));
    }

    @Test
    public void testUnmarshalStringToObject() throws Exception {

        // Given
        InputStream xmlStream = this.getClass().getResourceAsStream("creditRequestAvailable.xml");

        // When
        CreditRequestAvailable creditRequestAvailable =
                (CreditRequestAvailable) RootFullXmlHandling.unmarshalStreamToObject(xmlStream, CreditRequestAvailable.class);

        // Then
        assertThat(creditRequestAvailable.getId(), CoreMatchers.is(10L));
    }
}
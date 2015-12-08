package app.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Businessrules voor afgifte van vergunning
 */
@Component
public class VergunningPolicy {

    public boolean isToegestaan(Gebied gebied, Integer bouwhoogte) {
        if (bouwhoogte < 2)
            return false;

        if (gebied == Gebied.BUITENGEBIED && bouwhoogte <= 3)
            return true;

        if (gebied == Gebied.CENTRUM && bouwhoogte <= 40)
            return true;

        if (Arrays.asList(new Gebied[]{Gebied.NOORD, Gebied.OOST, Gebied.ZUID, Gebied.WEST}).contains(gebied) && bouwhoogte <= 10)
            return true;

        return false;
    }
}

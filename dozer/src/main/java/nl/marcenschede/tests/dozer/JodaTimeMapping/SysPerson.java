package nl.marcenschede.tests.dozer.JodaTimeMapping;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 19/09/15.
 */
public class SysPerson {
    private String voornaam;
    private LocalDate geboortedatum;
    private DateTime aanmelding;
    
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public DateTime getAanmelding() {
        return aanmelding;
    }

    public void setAanmelding(DateTime aanmelding) {
        this.aanmelding = aanmelding;
    }

    @Override
    public String toString() {
        return "SysPerson{" +
                "voornaam='" + voornaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", aanmelding=" + aanmelding +
                '}';
    }
}

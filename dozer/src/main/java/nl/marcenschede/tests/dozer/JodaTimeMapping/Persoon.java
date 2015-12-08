package nl.marcenschede.tests.dozer.JodaTimeMapping;

/**
 * Created by marc on 19/09/15.
 */
public class Persoon {
    private String naam;
    private String geboortedatum;
    private String aanmelddatum;
    
    public Persoon(String naam, String geboortedatum, String aanmelddatum) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.aanmelddatum = aanmelddatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getAanmelddatum() {
        return aanmelddatum;
    }

    public void setAanmelddatum(String aanmelddatum) {
        this.aanmelddatum = aanmelddatum;
    }
}

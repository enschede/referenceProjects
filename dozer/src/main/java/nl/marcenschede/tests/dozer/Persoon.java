package nl.marcenschede.tests.dozer;

/**
 * Created by marc on 19/09/15.
 */
public class Persoon {
    private String naam;
    private String plaats;

    public Persoon(String naam, String plaats) {
        this.naam = naam;
        this.plaats = plaats;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }
}

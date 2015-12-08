package java8.optionals;

/**
 * Created by marc on 21/12/14.
 */
public class Persoon {
    
    private String naam;
    private Adres adres;

    public Persoon() {
        naam = "Marc";
        adres = null;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "naam='" + naam + '\'' +
                '}';
    }
}

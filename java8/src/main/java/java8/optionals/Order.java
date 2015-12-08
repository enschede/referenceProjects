package java8.optionals;

/**
 * Created by marc on 21/12/14.
 */
public class Order {
    
    private Persoon persoon;

    public Order(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }
}

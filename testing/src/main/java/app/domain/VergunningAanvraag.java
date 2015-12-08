package app.domain;

/**
 * Created by marc on 21/05/15.
 */
public class VergunningAanvraag {
    
    private Gebied gebied;
    private Integer bouwhoogte;

    public Gebied getGebied() {
        return gebied;
    }

    public void setGebied(Gebied gebied) {
        this.gebied = gebied;
    }

    public Integer getBouwhoogte() {
        return bouwhoogte;
    }

    public void setBouwhoogte(Integer bouwhoogte) {
        this.bouwhoogte = bouwhoogte;
    }
}

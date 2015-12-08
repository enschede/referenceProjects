package nl.marcenschede.tests.dozer.abstractClass;

/**
 * Created by marc on 01/10/15.
 */
public class BierVoorkeur extends Voorkeur {
    
    private String biervoorkeur;

    public BierVoorkeur() {
    }

    public BierVoorkeur(String biervoorkeur) {
        this.biervoorkeur = biervoorkeur;
    }

    public String getBiervoorkeur() {
        return biervoorkeur;
    }

    public void setBiervoorkeur(String biervoorkeur) {
        this.biervoorkeur = biervoorkeur;
    }

    @Override
    public String toString() {
        return "BierVoorkeur{" +
                "biervoorkeur='" + biervoorkeur + '\'' +
                "} " + super.toString();
    }
}

package nl.marcenschede.tests.dozer.abstractClass;

/**
 * Created by marc on 01/10/15.
 */
public class WijnVoorkeur extends Voorkeur {
    
    private String wijnvoorkeur;

    public WijnVoorkeur() {
    }

    public WijnVoorkeur(String wijnvoorkeur) {
        this.wijnvoorkeur = wijnvoorkeur;
    }

    public String getWijnvoorkeur() {
        return wijnvoorkeur;
    }

    public void setWijnvoorkeur(String wijnvoorkeur) {
        this.wijnvoorkeur = wijnvoorkeur;
    }

    @Override
    public String toString() {
        return "WijnVoorkeur{" +
                "wijnvoorkeur='" + wijnvoorkeur + '\'' +
                "} " + super.toString();
    }
}

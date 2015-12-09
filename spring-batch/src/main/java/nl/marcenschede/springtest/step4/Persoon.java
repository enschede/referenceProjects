package nl.marcenschede.springtest.step4;

/**
 * Created by marc on 18/11/14.
 */
public class Persoon {
    
    private Long id;
    private String voornaam;
    private String achternaam;
    private Adres adres;

    public Persoon(Long id, String voornaam, String achternaam, Adres adres) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                '}';
    }
}

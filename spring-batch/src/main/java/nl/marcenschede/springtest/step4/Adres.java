package nl.marcenschede.springtest.step4;

/**
 * Created by marc on 18/11/14.
 */
public class Adres {

    private String straat;
    private Integer huisnummer;

    public Adres(String straat, Integer huisnummer) {
        this.straat = straat;
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public Integer getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(Integer huisnummer) {
        this.huisnummer = huisnummer;
    }
}

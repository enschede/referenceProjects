package nl.marcenschede.springtest.stax.writer;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marc on 31/03/15.
 */
@XmlRootElement(name = "MyElement")
public class MyElement {
    
    private Integer waarde;
    private String naam = "Marc";

    public Integer getWaarde() {
        return waarde;
    }

    public void setWaarde(Integer waarde) {
        this.waarde = waarde;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}

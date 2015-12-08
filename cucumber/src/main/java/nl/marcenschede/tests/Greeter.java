package nl.marcenschede.tests;

/**
 * Created by marc on 02/12/15.
 */
public class Greeter {

    private Sex sex;
    private String naam;

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getSaluation() {
        return "Geachte " +
                (sex == Sex.MALE ? "heer " : "mevrouw ") +
                naam;
    }
}

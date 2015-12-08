package java8.streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 25/11/14.
 */
public class Persoon {
  
  private String naam;
  private String plaats;
  private LocalDate geboortedatum;
  private List<String> hobbies = new ArrayList<>();

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

  public LocalDate getGeboortedatum() {
    return geboortedatum;
  }

  public void setGeboortedatum(LocalDate geboortedatum) {
    this.geboortedatum = geboortedatum;
  }

  public List<String> getHobbies() {
    return hobbies;
  }

  public void setHobbies(List<String> hobbies) {
    this.hobbies = hobbies;
  }

  @Override
  public String toString() {
    return "Persoon{" +
        "naam='" + naam + '\'' +
        ", plaats='" + plaats + '\'' +
        ", geboortedatum=" + geboortedatum +
        ", hobbies=" + hobbies +
        '}';
  }

  public static Persoon create(String naam, String plaats, String geboortedatum, List<String> hobbies) {
    Persoon persoon = new Persoon();
    persoon.setNaam(naam);
    persoon.setPlaats(plaats);

    persoon.setGeboortedatum(LocalDate.parse(geboortedatum, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    persoon.setHobbies(hobbies);
    
    return persoon;
  }
}

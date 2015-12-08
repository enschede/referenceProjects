package java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marc on 25/11/14.
 */
public class PersoonBuilder {
  
  private List<Persoon> persoonList = new ArrayList<Persoon>();
  
  public List<Persoon> build() {
    return persoonList;
  }
  
  public PersoonBuilder add(String naam, String plaats, String geboortedatum, String... hobbies) {
    persoonList.add(Persoon.create(naam, plaats, geboortedatum, Arrays.asList(hobbies)));
    return this;
  }
}

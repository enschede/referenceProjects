package java8.streams;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StreamTest {

    @Test
    public void shouldUseTwoPredicates() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        Predicate<Persoon> maurice =
                persoon -> persoon.getNaam().startsWith("Maurice");
        Predicate<Persoon> yvette =
                persoon -> persoon.getNaam().startsWith("Yvette");

        String p = personen.stream()
                .filter(maurice.or(yvette))
                .map(Persoon::getNaam)
                .collect(Collectors.joining(", "));

        assertThat(p, CoreMatchers.is("Yvette Enschede, Maurice Enschede"));
    }

    @Test
    public void distinctTests() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        List<String> alleSteden = personen.stream().map(Persoon::getPlaats).distinct().collect(Collectors.toList());
        assertThat(alleSteden.size(), is(2));
    }

    @Test
    public void sortedTests() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        List<String> allePersonenSortByName = personen.stream().map(Persoon::getNaam).sorted().collect(Collectors.toList());
        assertThat(allePersonenSortByName.get(0), is("Kitty Enschede"));
    }

    @Test
    public void limitTests() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        List<String> shortList = personen.stream().map(Persoon::getNaam).limit(1).collect(Collectors.toList());
        assertThat(shortList.size(), is(1));
        assertThat(shortList.get(0), is("Yvette Enschede"));
    }

    /**
     * peek voert een consumer uit in een reeks
     */
    @Test
    public void peekTests() {
        List<String> resultList = new ArrayList<>();

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Met 'peek' een extra consumer toevoegen
        personen.stream().map(Persoon::getNaam).peek(resultList::add).collect(Collectors.toList());

        assertThat(resultList.size(), is(4));
    }

    /**
     * forEach voert een consumer uit aan het einde van een reeks
     */
    @Test
    public void forEachTests() {
        List<String> resultList = new ArrayList<>();

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Met 'peek' een extra consumer toevoegen
        personen.stream().map(Persoon::getNaam).forEach(naam -> resultList.add(naam));

        assertThat(resultList.size(), is(4));
    }

    @Test
    public void shouldRemoveTwoElements() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Verwijder alle personen uit Enschede uit de lijst
        personen.removeIf(p -> p.getPlaats().equals("Enschede"));

        assertThat(personen.size(), is(2));
    }

    /**
     * FlatMap kan gebruikt worden als de output van een attribuut een lijst is en de elementen
     * uit deze lijst verder verwerkt moeten worden.
     */
    @Test
    public void flatMapTest() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek", "Mountainbiking").build();

        List<String> hobbies =
                personen.stream().flatMap(p -> p.getHobbies().stream()).collect(Collectors.toList());

        assertThat(hobbies.size(), is(5));
    }

    @Test
    public void comparatorTest() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23")
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek", "Mountainbiking").build();

        List<Persoon> sortedPersons =
                personen
                        .stream()
                        .sorted((p1, p2) -> p1.getGeboortedatum().compareTo(p2.getGeboortedatum()))
                        .collect(Collectors.toList());

        assertThat(sortedPersons.get(0).getNaam(), is("Marc Enschede"));
        assertThat(sortedPersons.get(3).getNaam(), is("Yvette Enschede"));
    }

    @Test
    public void maxTest() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23")
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek", "Mountainbiking").build();

        Optional<Persoon> persoon = personen.stream()
                .max((p1, p2) -> p1.getGeboortedatum().compareTo(p2.getGeboortedatum()));

        assertThat(persoon.get().getNaam(), is("Yvette Enschede"));
    }

    @Test
    public void minTest() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23")
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek", "Mountainbiking").build();

        Optional<Persoon> persoon = personen.stream()
                .min((p1, p2) -> p1.getGeboortedatum().compareTo(p2.getGeboortedatum()));

        assertThat(persoon.get().getNaam(), is("Marc Enschede"));
    }


}
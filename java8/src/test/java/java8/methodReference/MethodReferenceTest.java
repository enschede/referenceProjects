package java8.methodReference;

import org.hamcrest.Matchers;
import org.junit.Test;
import java8.streams.Persoon;
import java8.streams.PersoonBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by marc on 30/10/15.
 * See for examples on https://blog.idrsolutions.com/2015/02/java-8-method-references-explained-5-minutes/
 */
public class MethodReferenceTest {

    /**
     * Reference To an Instance Method Of An Arbitrary Object Of A Particular Type
     */
    @Test
    public void methodReferencing() {

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Op deze manier kan een strategie of formulie geinjecteerd worden
        String naam = printAttribuut(personen.get(0), Persoon::getNaam);

        assertThat(naam, is("Yvette Enschede"));
    }

    private String printAttribuut(Persoon persoon, Function<Persoon, String> f) {
        String naam = f.apply(persoon);

        return naam;
    }

    /**
     * Referencing to a biFunction
     */
    @Test
    public void methodBiFunctionReferencing() {

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        BiFunction<List<Persoon>, Integer, String> function = (innerPersonen, integer) -> innerPersonen.get(integer).getNaam();
        String naam = printAttribuut(personen, 0, function);

        assertThat(naam, is("Yvette Enschede"));
    }

    private String printAttribuut(List<Persoon> personen, Integer index, BiFunction<List<Persoon>, Integer, String> f) {
        String naam = f.apply(personen, index);

        return naam;
    }

    /**
     * Using the .andThen method to add a post processing function to a function
     */
    @Test
    public void methodAndThenTesting() {

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Haal een functie referentie op
        Function<Persoon, String> function = Persoon::getNaam;
        Function<Persoon, String> greeterFunction = function.andThen(this::greeter);
        Function<Persoon, String> exclamationFunction = function.andThen(this::addExclamationSign);
        Function<Persoon, String> exclamationGreeterFunction = function.andThen(this::greeter).andThen(this::addExclamationSign);

        Persoon yvette = personen.get(0);

        assertThat(function.apply(yvette), is("Yvette Enschede"));
        assertThat(greeterFunction.apply(yvette), is("Hello Yvette Enschede"));
        assertThat(exclamationFunction.apply(yvette), is("Yvette Enschede!"));
        assertThat(exclamationGreeterFunction.apply(yvette), is("Hello Yvette Enschede!"));
    }

    private String greeter(String input) {
        return "Hello " + input;
    }

    private String addExclamationSign(String input) {
        return input + "!";
    }

    /**
     * Using the .compose method to add a pre processing function to a function
     */
    @Test
    public void methodComposeTesting() {

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        Persoon yvette = personen.get(0);

        Function<String, String> greeter = s -> "Hello " + s;
        Function<Persoon, String> nameGreeter = greeter.compose(Persoon::getNaam);

        assertThat(nameGreeter.apply(yvette), is("Hello Yvette Enschede"));
    }

    /**
     * Reference To An Instance Method Of A Particular Object
     */
    @Test
    public void printToObjectReference() {

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // System.out::println voldoet aan de definitie van een Consumer
        personen.stream().map(Persoon::getNaam).forEach(System.out::println);
        // Zelfde al hierboven (druk maar eens op IntelliJ Alt-Enter)
        personen.stream().map(Persoon::getNaam).forEach( naam -> { System.out.println(naam); });
    }

    /**
     * Testing of collectionAdd function
     */
    @Test
    public void testingOfCollectionAdd() {

        Set<String> namen = new HashSet<>();

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // Persoon::getNaam hoeft geen static te zijn. De te mappen objecten zijn van het type Persoon
        personen.stream().map(Persoon::getNaam).forEach(namen::add);

        assertThat(namen.size(), is(4));
        // Contains controleert of alle elementen in volorde aanwezig zijn
        assertThat(namen, Matchers.contains("Yvette Enschede", "Maurice Enschede", "Marc Enschede", "Kitty Enschede"));
        // Controleer of alle elementen in willekeurige volgorde aanwezig zijn
        assertThat(namen, Matchers.containsInAnyOrder("Yvette Enschede", "Marc Enschede", "Maurice Enschede", "Kitty Enschede"));
        // HasItem controleert of dat ene element ook aanwezig is
        assertThat(namen, Matchers.hasItem("Maurice Enschede"));
        // Controleer of 2 willekeurige elementen aanwezig zijn
        assertThat(namen, Matchers.hasItems("Maurice Enschede", "Yvette Enschede"));
    }

    /**
     * Using a static function
     */
    @Test
    public void shouldUseGenericFunction() {

        Set<String> namen = new HashSet<>();

        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        // getNaam moet static zijn als het object niet geïnstantieerd is
        personen.stream().map(GenericFunctions::getNaam).forEach(namen::add);

        assertThat(namen.size(), is(4));
        assertThat(namen, Matchers.contains("Yvette Enschede", "Maurice Enschede", "Marc Enschede", "Kitty Enschede"));
    }
}

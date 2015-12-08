package java8.optionals;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OptionalTest {

    /**
     * Testen van een 'map' van een optional. Deze levert een nieuwe optional op.
     */
    @Test
    public void filterOptional() {
        Optional<Persoon> persoonOptional = Optional.ofNullable(new Persoon());

        assertThat(persoonOptional.isPresent(), is(true));
        assertThat(persoonOptional.get().getNaam(), is("Marc"));

        Optional<Persoon> result = persoonOptional.filter(p -> p.getNaam().equals("Jan"));
        assertThat(result.isPresent(), is(false));
    }

    /**
     * Test van een .of die geen null mag bevatten
     */
    @Test(expected = NullPointerException.class)
    public void filterOptionalWithNull() {
        Optional<Persoon> persoonOptional = Optional.of(null);
    }

    /**
     * Test van een 'ifPresent' op een optional. Als aanwezig, dan wordt de waarde naar een consumer gezonden.
     */
    @Test
    public void consumerOptional() {
        Optional<Persoon> persoonOptional = Optional.ofNullable(new Persoon());

        persoonOptional.ifPresent(persoon -> { System.out.println(persoon.getNaam()); });
    }

    /**
     * Test van een 'orElse', levert een default object op.
     */
    @Test
    public void orElseOptional() {
        Optional<Persoon> persoonOptional = Optional.empty();

        Persoon result = persoonOptional.orElse(new Persoon());
        assertThat(result.getNaam(), is("Marc"));
    }

    @Test
    public void mapAndFlatMapOptional() {
        // Order bevat een person object
        Optional<SalesExecutive> salesExecutive =
                Optional.of(new SalesExecutive(new Order(new Persoon())));

        // Een simpele 'map' levert een optional van een optional op
        Optional<Optional<Order>> optionalOptional = salesExecutive.map(se -> se.getOrder());

        Optional<Order> orderOptional = salesExecutive.flatMap(se -> se.getOrder());

        // se.getOrder levert een Optional<Order>, daarom flatmap
        // order.getPerson levert een Person, daarom een map
        // Antwoord is altijd een Optional, omdat de result waarde van het object null kan zijn
        Optional<Persoon> p = salesExecutive.flatMap(se -> se.getOrder()).map(order -> order.getPersoon());

        assertThat(p.get().getNaam(), is("Marc"));
    }

    @Test
    public void mapAndFlatMapOptionalShorteNotation() {
        Optional<SalesExecutive> salesExecutive =
                Optional.of(new SalesExecutive(new Order(new Persoon())));

        Optional<Persoon> persoon = salesExecutive.flatMap(SalesExecutive::getOrder).map(Order::getPersoon);

        assertThat(persoon.get().getNaam(), is("Marc"));
        persoon.ifPresent(System.out::println);
    }

    /**
     * Lange lijst van null-waarden. Iedere map levert weer een lege optional op.
     */
    @Test
    public void shouldFollowNullCascade() {
        Optional<Persoon> persoon = Optional.of(new Persoon());

        Optional<String> landnaamOptional = persoon.map(Persoon::getAdres).map(Adres::getLand).map(Land::getLandnaam);

        assertThat(landnaamOptional.isPresent(), is(false));
    }

}
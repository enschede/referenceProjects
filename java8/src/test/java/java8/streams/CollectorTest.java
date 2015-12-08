package java8.streams;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;

/**
 * Created by marc on 30/10/15.
 */
public class CollectorTest {

    @Test
    public void shouldJoinStrings() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();


        String result1 = personen.stream().map(p -> p.getNaam()).collect(Collectors.joining(", "));
        assertThat(result1, CoreMatchers.is("Yvette Enschede, Maurice Enschede, Kitty Enschede, Marc Enschede"));

        String result2 = personen.stream().map(p -> p.getNaam()).collect(Collectors.joining(", ", "prefix ", " suffix"));
        assertThat(result2, CoreMatchers.is("prefix Yvette Enschede, Maurice Enschede, Kitty Enschede, Marc Enschede suffix"));
    }

    @Test
    public void shouldGroupBy() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();


        Map<String, List<Persoon>> map = personen.stream().collect(Collectors.groupingBy(Persoon::getPlaats));

        assertThat(map.size(), Is.is(2));
        assertThat(map.keySet(), Matchers.containsInAnyOrder("Enschede", "Annen"));
    }

    @Test
    public void shouldPartitionBy() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        Map<Boolean, List<Persoon>> map =
                personen.stream().collect(Collectors.partitioningBy(p -> p.getPlaats().equals("Enschede")));

        assertThat(map.size(), Is.is(2));
    }

    @Test
    public void shouldCombineComplex() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        String result = personen.parallelStream()
                .filter(p -> p.getPlaats().equals("Annen"))
                        // Collector heeft 3 typen; de input class, de katalisator class en de output class
                .collect(new Collector<Persoon, StringBuilder, String>() {

                    @Override
                    public Supplier<StringBuilder> supplier() {
                        return new Supplier<StringBuilder>() {
                            @Override
                            public StringBuilder get() {
                                System.out.println("supplier::constructor");
                                return new StringBuilder();
                            }
                        };
                    }

                    @Override
                    public BiConsumer<StringBuilder, Persoon> accumulator() {
                        return new BiConsumer<StringBuilder, Persoon>() {
                            @Override
                            public void accept(StringBuilder o, Persoon persoon) {
                                System.out.println("accumulator::accept, persoon = " + persoon);
                                o.append(o.length() == 0 ? persoon.getNaam() : ", " + persoon.getNaam());
                            }
                        };
                    }

                    @Override
                    public BinaryOperator<StringBuilder> combiner() {
                        return new BinaryOperator<StringBuilder>() {
                            @Override
                            public StringBuilder apply(StringBuilder o1, StringBuilder o2) {
                                System.out.println("combiner::apply, o1=" + o1.toString() + ", o2=" + o2.toString());
                                return o1.append(o2);
                            }
                        };
                    }

                    @Override
                    public Function<StringBuilder, String> finisher() {
                        return new Function<StringBuilder, String>() {
                            @Override
                            public String apply(StringBuilder o) {
                                System.out.println("finisher::apply");
                                return o.toString();
                            }
                        };
                    }

                    @Override
                    public Set<Characteristics> characteristics() {
                        System.out.println("characteristics::new HashSet");
                        return new HashSet<Characteristics>() {
                        };
                    }
                });


        assertThat(result, CoreMatchers.is("Yvette EnschedeMaurice Enschede"));
    }


}

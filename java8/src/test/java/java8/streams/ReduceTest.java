package java8.streams;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static org.junit.Assert.assertThat;

/**
 * Testen van diverse reducers.
 */
public class ReduceTest {

    @Test
    public void shouldJoinStrings() {
        List<Persoon> personen = new PersoonBuilder()
                .add("Yvette Enschede", "Annen", "1999-06-18", "Voetbal")
                .add("Maurice Enschede", "Annen", "1998-03-10", "ICT")
                .add("Kitty Enschede", "Enschede", "1967-06-23", "Koken")
                .add("Marc Enschede", "Enschede", "1966-02-22", "ICT", "Politiek").build();

        BinaryOperator<String> joiner = (vorige, naam) -> vorige == null ? naam : vorige + ", " + naam;

        String result1 = personen.stream().map(p -> p.getNaam()).reduce(null, joiner);
        assertThat(result1, CoreMatchers.is("Yvette Enschede, Maurice Enschede, Kitty Enschede, Marc Enschede"));
    }

    /**
     * Reductie met 1 argument
     */
    @Test
    public void shouldRecureWithOneArgument() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // De apply wordt 4x aangeroepen, pas vanaf het 2 element. Dan geldt het eerste element als total.
        // Bij het derde element wordt geldt het totaal van element 2 als totaal.
        Optional<Integer> total = integers.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                System.out.printf("BinaryOperator: integer=%d, integer=%d\n", integer, integer2);
                return integer + integer2;
            }
        });

        assertThat(total.get(), Matchers.is(15));
    }

    /**
     * Reductie met 2 argumenten
     */
    @Test
    public void shouldRecureWithTwoArguments() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // De binary operator wordt 5x aangeroepen, met als basis de identity
        Integer total = integers.stream().reduce(new Integer(0), new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                System.out.printf("BinaryOperator: integer=%d, integer=%d\n", integer, integer2);
                return integer + integer2;
            }
        });

        assertThat(total, Matchers.is(15));
    }

    /**
     * Reductie met 2 argumenten in parallelstream
     */
    @Test
    public void shouldRecureWithTwoArgumentsParallel() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // De binary operator wordt 9x aangeroepen, met als basis de identity
        Integer total = integers.parallelStream().reduce(new Integer(0), new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                System.out.printf("BinaryOperator: integer=%d, integer=%d\n", integer, integer2);
                return integer + integer2;
            }
        });

        assertThat(total, Matchers.is(15));
    }

    /**
     * Reduce met 3 argumenten, heeft geen functionele waarbij single stream
     */
    @Test
    public void shouldCalculateTotalWithSingleStream() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // De bi-function doet de optelling, de bi-operator komt er niet aan te pas
        Integer total = integers.stream().reduce(new Integer(0),
                new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer1, Integer integer2) {
                        System.out.printf("BiFunction: integer=%d, integer=%d\n", integer1, integer2);
                        return integer1 + integer2;
                    }
                },
                new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer aDouble, Integer aDouble2) {
                        System.out.printf("BinaryOperator: integer1=%d, integer2=%d\n", aDouble, aDouble2);
                        return aDouble + aDouble2;
                    }
                }
        );
        assertThat(total, Matchers.is(15));
    }

    /**
     * Reduce met 3 argumenten, lijkt alleen van belang bij parallel stream
     */
    @Test
    public void shouldCalculateTotalWithParallelStream() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // In de parallelstream doet de bi-operator de optelling
        Integer total = integers.parallelStream().reduce(new Integer(0),
                new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer1, Integer integer2) {
                        System.out.printf("BiFunction: integer=%d, integer=%d\n", integer1, integer2);
                        return integer1 + integer2;
                    }
                },
                new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer aDouble, Integer aDouble2) {
                        System.out.printf("BinaryOperator: integer1=%d, integer2=%d\n", aDouble, aDouble2);
                        return aDouble + aDouble2;
                    }
                }
        );

        assertThat(total, Matchers.is(15));
    }
}

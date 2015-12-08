package java8.completableFuture;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * Created by marc on 05/11/15.
 */
public class CompletableFutureTest {

    @Test
    public void supplierCompletableFutureTest() throws ExecutionException, InterruptedException {

        Supplier<String> stringSupplier = () -> "Hallo wereld";
        String result = CompletableFuture.supplyAsync(stringSupplier).get();

        Assert.assertThat(result, Matchers.is("Hallo wereld"));
    }

    private String result = null;

    @Test
    public void supplierCompletableFutureTest2() throws ExecutionException, InterruptedException {

        // Complex calculation
        Supplier<String> stringSupplier = () -> PiCalculator.computePi(40).toString();

        // Maak completable future, pakt de supplier en zet antwoord terug
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(stringSupplier).thenAccept(s -> {
            this.result = s;
        });

        // Wacht tot taak klaar is
        cf.get();

        Assert.assertThat(result, Matchers.is("3.1415926535897932384626433832795028841972"));
    }

    @Test
    public void supplierCompletableFutureTest3() throws ExecutionException, InterruptedException {

        // Maak completable future, pakt de supplier en zet antwoord terug
        CompletableFuture<Void> cf1 = CompletableFuture.supplyAsync(() -> PiCalculator.computePi(400).toString()).thenAccept(s -> {
            this.result = s;
        });
        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(() -> PiCalculator.computePi(40).toString()).thenAccept(s -> {
            this.result = s;
        });

        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2);

        // Wacht tot taak klaar is
        cfAll.get();

        Assert.assertThat(result, Matchers.is("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094"));
    }

}

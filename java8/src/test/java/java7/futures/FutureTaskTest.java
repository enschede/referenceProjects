package java7.futures;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertThat;

/**
 * Created by marc on 31/10/15.
 */
public class FutureTaskTest {

    @Test
    public void futureTest() throws InterruptedException, ExecutionException, TimeoutException {

        final String naam = "Marc";

        // De future task kan een taak in een container, de callable, runnen.
        // Binnen de callable kan gebruik gemaakt worden van finals 'van buiten'.
        FutureTask<String> futureTask1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hallo " + naam;
            }
        });

        // Vergeet niet de taak te starten, anders komt het antwoord nooit!
        futureTask1.run();

        String result = futureTask1.get(10, TimeUnit.SECONDS);

        assertThat(result, Matchers.is("Hallo Marc"));
    }
}

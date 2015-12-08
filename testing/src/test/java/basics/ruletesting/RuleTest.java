package basics.ruletesting;

import org.junit.Rule;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * Created by marc on 24/05/15.
 */
public class RuleTest {
    @Rule
    public TestLogger logger = new TestLogger();

    @Test
    public void bla() {
        final Logger log = logger.getLogger();
        log.warning("Your test is showing!");
    }
}

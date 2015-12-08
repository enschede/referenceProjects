package basics.ruletesting;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.logging.Logger;

/**
 * Created by marc on 24/05/15.
 */
public class TestLogger implements TestRule {
    private Logger logger;

    public Logger getLogger() {
        return this.logger;
    }
    
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            
            @Override
            public void evaluate() throws Throwable {
                logger = Logger.getLogger(description.getTestClass().getName() + '.' + description.getDisplayName());
                try {
                    logger.warning("Before evalute");
                    base.evaluate();
                    logger.warning("After evalute");
                } finally {
                    logger = null;
                }
            }
        };
    }
}

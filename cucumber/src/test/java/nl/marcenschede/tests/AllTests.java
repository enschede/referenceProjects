package nl.marcenschede.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Plaats waar het test verslag wordt opgeslagen
        format = {"pretty", "html:target/cucumber"},

        // Package met daarin de fixures
        glue = {"cucumber.steps", "nl.marcenschede.tests.steps"},

        // Plaats waarde features gevonden worden
        // Default: hetzelfde path als de package van deze class
        features = "classpath:cucumber/",

        // False (default): ignore tests waarvan de feature niet gemapt kan worden op een fixure
        // True: faal als feature niet op fixure gemapt kan worden
        strict = false
)
public class AllTests {

}

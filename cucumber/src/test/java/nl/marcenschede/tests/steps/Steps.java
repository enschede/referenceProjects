package nl.marcenschede.tests.steps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.marcenschede.tests.Greeter;
import nl.marcenschede.tests.Sex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;

import java.util.Map;

public class Steps {

    private Greeter greeter = null;

    @Given("^I have a person named \"([^\"]*)\"$")
    public void i_have_a_person_name(String name) throws Throwable {

        greeter = new Greeter();
        greeter.setNaam(name);
    }

    @Given("^I have a person$")
    public void i_have_a_person() throws Throwable {
        greeter = new Greeter();
    }

    @Given("^saluations are:$")
    public void saluations_are(DataTable arg1) throws Throwable {
        Map<String, String> data = arg1.asMap(String.class, String.class);
        data.forEach((k,v) -> System.out.println(k + " maps to " + v));
    }

    @When("^the person is a \"([^\"]*)\"$")
    public void the_person_is_a(String sex) throws Throwable {

        greeter.setSex(Sex.valueOf(sex));
    }

    @When("^the person is named \"([^\"]*)\"$")
    public void the_person_is_named(String name) throws Throwable {
        greeter.setNaam(name);
    }

    @Then("^the saluation is \"([^\"]*)\"$")
    public void the_saluation_is(String expectedSaluation) throws Throwable {

        Assert.assertTrue(greeter.getSaluation().equals(expectedSaluation));
    }

    @Then("^the persons sex is \"([^\"]*)\"$")
    public void the_persons_sex_is(String sex) throws Throwable {
        Assert.assertTrue(greeter.getSex().toString().equals(sex));
    }

    @After
    public void after(Scenario scenario) {
        if(scenario.isFailed()) {
            // Add things to do in after method
        }
    }

}

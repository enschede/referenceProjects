package basics.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by marc on 21/05/15.
 */
public class MyTester extends TypeSafeMatcher<Integer> {
    @Override
    protected boolean matchesSafely(Integer val) {
        return (val%2)==0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("even number");
    }
    
    @Factory
    public static <T> Matcher<Integer> even() {
        return new MyTester();
    }
}

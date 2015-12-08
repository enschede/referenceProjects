package basics.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.SubstringMatcher;

public class ContainsWithWhitespaces extends SubstringMatcher {
    
    public ContainsWithWhitespaces(String substring) {
        super(substring.replaceAll("\\s", ""));
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.replaceAll("\\s", "").indexOf(substring) > 0;
    }

    @Override
    protected String relationship() {
        return "containing with whitespaces";
    }

    @Factory
    public static Matcher<String> containsString(String substring) {
        return new ContainsWithWhitespaces(substring);
    }

}

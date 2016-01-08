package nl.marcenschede.springtest.flatfilewriter;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;

import java.text.ParseException;

public class CountingPersonReader implements ItemReader<Person> {


    long teller = 0L;
    JobParameters jobParameters;
    
    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (teller == 11)
            return null;

        return new Person(teller++, "Test", "Person", new Address("MyStreet", 6));
    }
}

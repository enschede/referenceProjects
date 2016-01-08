package nl.marcenschede.springtest.simplejob.step2;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;

import java.text.ParseException;

public class MyItemReader4 implements ItemReader<Persoon> {


    long teller = 0L;
    JobParameters jobParameters;
    
    @Override
    public Persoon read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (teller == 11)
            return null;

        return new Persoon(teller++, "Test", "Person", new Adres("MyAddress", 6));
    }
}

package nl.marcenschede.springtest.stax.writer;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Created by marc on 31/03/15.
 */
public class CountingReader implements ItemReader<MyElement> {
    
    private int teller = 0;
    
    @Override
    public MyElement read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if(teller==21)
            return null;
        
        MyElement myElement = new MyElement();
        myElement.setWaarde(teller++);
        
        return myElement;
    }
}

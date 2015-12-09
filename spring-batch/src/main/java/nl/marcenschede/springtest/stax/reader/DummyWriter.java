package nl.marcenschede.springtest.stax.reader;

import nl.marcenschede.springtest.stax.reader.valueobjects.Footer;
import nl.marcenschede.springtest.stax.reader.valueobjects.Header;
import nl.marcenschede.springtest.stax.reader.valueobjects.Item;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by marc on 22/04/15.
 */
public class DummyWriter implements ItemWriter<Object> {
    
    private int aantalRegels = 0;
    
    @Override
    public void write(List<?> items) throws Exception {
        for (Object item : items) {
            if (item instanceof Header)
                System.out.println("Account = " + ((Header) item).getAccount());
            
            if (item instanceof Item)
                System.out.println("Name = " + ((Item) item).getName() + ", amount = " + ((Item)item).getAmount());
            
            if (item instanceof Footer)
                System.out.println("Footer total amount = " + ((Footer) item).getTotalAmount());
            
            if( !(item instanceof Header || item instanceof Item || item instanceof Footer) )
                System.out.println("Other object = " + item.toString());
        }
        
        aantalRegels += items.size();
    }
    
    @AfterStep
    public void afterStep() {
        System.out.println("Number of lines = " + aantalRegels);
    }
}

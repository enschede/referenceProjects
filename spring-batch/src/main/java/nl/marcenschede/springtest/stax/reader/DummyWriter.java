package nl.marcenschede.springtest.stax.reader;

import nl.marcenschede.springtest.stax.reader.valueobjects.Footer;
import nl.marcenschede.springtest.stax.reader.valueobjects.Header;
import nl.marcenschede.springtest.stax.reader.valueobjects.Item;
import org.apache.log4j.Logger;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by marc on 22/04/15.
 */
public class DummyWriter implements ItemWriter<Object> {

    final static Logger logger = Logger.getLogger(DummyWriter.class);
    private int aantalRegels = 0;
    
    @Override
    public void write(List<?> items) throws Exception {
        logger.debug(String.format("itemsList.size = [%d]", items.size()));

        for (Object item : items) {
            if (item instanceof Header)
                logger.debug(String.format("Account = [%s]", ((Header) item).getAccount()));
            
            if (item instanceof Item)
                logger.debug(String.format("Name = [%s], amount = [%s]",
                        ((Item) item).getName(), ((Item)item).getAmount()));
            
            if (item instanceof Footer)
                logger.debug(String.format("Footer total amount = [%s]", ((Footer) item).getTotalAmount()));
            
            if( !(item instanceof Header || item instanceof Item || item instanceof Footer) )
                logger.debug(String.format("Other object = [%s]", item.toString()));
        }
        
        aantalRegels += items.size();
    }
    
    @AfterStep
    public void afterStep() {
        logger.debug(String.format("Number of lines = [%d]", aantalRegels));
    }
}

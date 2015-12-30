package nl.marcenschede.springtest.stax.camt053;

import nl.marcenschede.springtest.stax.camt053.xsd.GroupHeader42;
import nl.marcenschede.springtest.stax.camt053.xsd.ReportEntry2;
import nl.marcenschede.springtest.stax.camt053.xsd.TotalTransactions2;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by marc on 12/05/15.
 */
public class CamtProcessor implements ItemProcessor<Object, CamtItem> {
    
    private GroupHeader42 groupHeader42;
    
    @Override
    public CamtItem process(Object itemFromReader) throws Exception {
        System.out.printf("itemFromReader.class = [%s]", itemFromReader.getClass().getName());

        if(itemFromReader instanceof GroupHeader42) {
            groupHeader42 = (GroupHeader42)itemFromReader;
            return null;
        }
            
        if(itemFromReader instanceof TotalTransactions2) {
            System.out.print(((TotalTransactions2)itemFromReader).toString());
        }
        
        if(itemFromReader instanceof ReportEntry2) {
            return new CamtItem(groupHeader42, (ReportEntry2)itemFromReader);
        }
        
        return null;
    }
}

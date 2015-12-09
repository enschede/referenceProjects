package nl.marcenschede.springtest.error;

import nl.marcenschede.springtest.app.LogItem;
import nl.marcenschede.springtest.app.LogItemRepository;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marc on 17/03/15.
 */
public class ErrorTestReader implements ItemReader<Integer> {

    // Volgorde van handelingen (geen exception)
    // beforeRead
    // read
    // afterRead
    
    // Volgorde van handelingen (exception)
    // beforeRead
    // read
    // onReadError (geen transaction context)
    // ...process... van ItemProcessor
    // onSkipInRead (met transaction context)
    
    int teller = 0;
    
    @Autowired
    private LogItemRepository logItemRepository;

    @BeforeRead
    public void beforeRead() {
        System.out.println("ErrorTestReader::beforeRead");
        
        logItemRepository.save(new LogItem(-1, "ErrorTestReader::beforeRead"));
    }

    @AfterRead
    public void afterRead(Object item) {
        System.out.println("ErrorTestReader::afterRead, item = " + item);

        logItemRepository.save(new LogItem((Integer) item, "ErrorTestReader::afterRead"));
    }

    @OnReadError
    public void onReadError(Exception e) {
        System.out.println("ErrorTestReader::onReadError, exception = " + e.getMessage());

        logItemRepository.save(new LogItem(-1, "ErrorTestReader::onReadError, exception = " + e.getMessage()));
    }
    
    @OnSkipInRead
    public void onSkipInRead(Exception e) {
        System.out.println("ErrorTestReader::onSkipInRead, exception = " + e.getMessage());

        logItemRepository.save(new LogItem(-1, "ErrorTestReader::onSkipInRead, exception = " + e.getMessage()));
    }

    @BeforeJob
    public void beforeJob() {
        System.out.println("ErrorTestReader::beforeJob");
    }

    @AfterJob
    public void afterJob() {
        System.out.println("ErrorTestReader::afterJob");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("ErrorTestReader::beforeStep");

        logItemRepository.save(new LogItem(-1, "ErrorTestReader::beforeStep"));
    }

    @AfterStep
    public void afterStep() {
        System.out.println("ErrorTestReader::afterStep");

        logItemRepository.save(new LogItem(-1, "ErrorTestReader::afterStep"));
    }

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("ErrorTestReader::read, teller = " + ++teller);

        logItemRepository.save(new LogItem(teller, "ErrorTestReader::read"));

        if (teller == 31)
            return null;

        if(teller==10)
            throw new FunctionException("Teller mag geen 10 zijn");
        if(teller==15)
            throw new FunctionException("Teller mag geen 15 zijn");
        
        return Integer.valueOf(teller);
    }
}

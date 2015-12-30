package nl.marcenschede.springtest.error;

import nl.marcenschede.springtest.app.LogItem;
import nl.marcenschede.springtest.app.LogItemRepository;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marc on 17/03/15.
 */
public class ErrorTestProcessor implements ItemProcessor<Integer, String> {

    // Volgorde van handelen (geen exception):
    // beforeProcess
    // process
    // afterProcess
    
    // Volgorde van handelen (exception):
    // beforeProcess
    // process
    // onProcessError (geen transactie context)
    // ...write... van ItemWriter
    // onSkipInProcess (met transactie context)
    
    // Alle acties worden uitgevoerd vanuit hetzelfde object, de repository is dus in alle methods beschikbaar
    @Autowired
    private LogItemRepository logItemRepository;
    
    @BeforeProcess
    public void beforeProcess(Object in) {
        System.out.println("ErrorTestProcessor::beforeProcess, item = " + in);

        logItemRepository.save(new LogItem((Integer) in, "ErrorTestProcessor::beforeProcess"));
    }

    @AfterProcess
    public void afterProcess(Object in, Object out) {
        System.out.println("ErrorTestProcessor::afterProcess, in=" + in + ", out=" + out);

        logItemRepository.save(new LogItem((Integer) in, "ErrorTestProcessor::afterProcess"));
    }

    @OnProcessError
    public void onProcessError(Object in, Exception e) {
        System.out.println("ErrorTestProcessor::onProcessError, in=" + in + ", exception=" + e.getMessage());

        // Heeft GEEN transactie context, save is dus voor nop...
        logItemRepository.save(new LogItem((Integer) in, "ErrorTestProcessor::onProcessError, exception = " + e.getMessage()));
    }

    @OnSkipInProcess
    public void onSkipInProcess(Object in, Exception e) {
        System.out.println("ErrorTestProcessor::onSkipInProcess, in=" + in + ", exception=" + e.getMessage());

        // Heeft een transactie context
        logItemRepository.save(new LogItem((Integer) in, "ErrorTestProcessor::onSkipInProcess, exception = " + e.getMessage()));
    }

    // BeforeJob en AfterJob doen helemaal niets!
    
    @BeforeJob
    public void beforeJob() {
        System.out.println("ErrorTestProcessor::beforeJob");
    }

    @AfterJob
    public void afterJob() {
        System.out.println("ErrorTestProcessor::afterJob");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("ErrorTestProcessor::beforeStep");

        // Heeft een transactie context
        logItemRepository.save(new LogItem(-1, "ErrorTestProcessor::beforeStep"));
    }

    @AfterStep
    public void afterStep() {
        System.out.println("ErrorTestProcessor::afterStep");

        // Heeft een transactie context
        logItemRepository.save(new LogItem(-1, "ErrorTestProcessor::afterStep"));
    }

    @Override
    public String process(Integer item) throws Exception {
        System.out.println("ErrorTestProcessor::process, item = " + item);

        logItemRepository.save(new LogItem(item, "ErrorTestProcessor::process"));

//        if(item==12)
//            throw new FunctionException("Processing of 12 is probitit!");
        if(item==13)
            throw new FunctionException("Processing of 13 is probitit!");
        
        return String.valueOf(item) + ".00";
    }
}

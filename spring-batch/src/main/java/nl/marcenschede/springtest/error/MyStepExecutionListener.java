package nl.marcenschede.springtest.error;

import nl.marcenschede.springtest.app.LogItem;
import nl.marcenschede.springtest.app.LogItemRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marc on 17/03/15.
 */
public class MyStepExecutionListener implements StepExecutionListener {

    @Autowired
    private LogItemRepository logItemRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("MyStepExecutionListener beforeStep");

        logItemRepository.save(new LogItem(-1, "MyStepExecutionListener::beforeStep"));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("MyStepExecutionListener afterStep");

        logItemRepository.save(new LogItem(-1, "MyStepExecutionListener::afterStep"));
        return null;
    }
}

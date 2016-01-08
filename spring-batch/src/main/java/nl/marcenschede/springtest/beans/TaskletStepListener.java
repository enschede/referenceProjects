package nl.marcenschede.springtest.beans;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Created by marc on 10/11/14.
 */
public class TaskletStepListener implements StepExecutionListener {

    final static Logger logger = Logger.getLogger(MyTasklet.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Started with stepExecution = " + stepExecution.toString());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getJobExecution().getExecutionContext().getString("bla").equals("bla") )
            return ExitStatus.COMPLETED;
        else
            return ExitStatus.FAILED;
    }
}

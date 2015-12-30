package nl.marcenschede.springtest.beans;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

public class MyTasklet implements Tasklet, InitializingBean {

    final static Logger logger = Logger.getLogger(MyTasklet.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putString("bla","bla");

        logger.debug("Started");

        return RepeatStatus.FINISHED;
    }
}

package nl.marcenschede.springtest.parallel;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ParallelWriter implements ItemWriter<String> {

    final static Logger logger = Logger.getLogger(ParallelWriter.class);

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        logger.debug(String.format("Started step [%s]", stepExecution.getStepName()));
    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        logger.debug(String.format("items.length=[%d]", items.size()));
        for(String item : items)
            logger.debug(String.format("item=[%s]", item));
    }
}

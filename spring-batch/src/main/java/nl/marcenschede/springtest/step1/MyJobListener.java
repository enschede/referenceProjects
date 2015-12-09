package nl.marcenschede.springtest.step1;

import org.springframework.batch.core.*;

/**
 * Created by marc on 10/11/14.
 */
public class MyJobListener implements JobExecutionListener {
    
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("MyJobListener::beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("MyJobListener::afterJob");
    }
}

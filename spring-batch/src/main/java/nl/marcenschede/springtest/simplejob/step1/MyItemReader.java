package nl.marcenschede.springtest.simplejob.step1;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.*;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;

import java.text.ParseException;

public class MyItemReader implements ItemReader<String> {


    long teller = 0L;
    JobParameters jobParameters;
    
    // Deze wordt niet uitgevoerd. Dit object wordt pas aangemaakt bij create van de step
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("MyItemReader::beforeJob executed");
    }

    // Deze wordt niet uitgevoerd. Dit object wordt pas aangemaakt bij create van de step
    @AfterJob
    public ExitStatus afterJob(JobExecution jobExecution) {
        System.out.println("MyItemReader::afterJob executed");
        return null;
    }

    // Let op dat hier org.springframework.batch.core.StepExecution wordt gebruikt.
    // Er is nog een andere StepExecution die het feestje verstiert.
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("MyItemReader::beforeStep executed. stepContext.getJobParameters()=" + stepExecution.getJobParameters().toString());
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("MyItemReader::afterStep executed");
        return null;
    }

    @BeforeRead
    public void beforeRead() {
        System.out.println("MyItemReader::beforeRead");
    }
    
    @BeforeChunk
    public void beforeChunk() {
        System.out.println("MyItemReader::beforeChunk");
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (teller == 11)
            return null;

        return String.valueOf(++teller);
    }
}

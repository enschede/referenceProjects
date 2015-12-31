package nl.marcenschede.springtest.app;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-configuration.xml",
        "classpath:application-jobs-flatfile-writer.xml",
        "classpath:application-ds.xml"})
public class LateBindingJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void executeParallelStepsJob()
            throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis())
                        .addString("outfile", "file:target/test-outputs/output.txt")
                        .toJobParameters();
        ;

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        Assert.assertThat(jobExecution.getStatus(), CoreMatchers.is(BatchStatus.COMPLETED));
    }

}

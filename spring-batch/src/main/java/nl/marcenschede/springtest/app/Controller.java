package nl.marcenschede.springtest.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job ioSampleJob;
    
    @Autowired
    Job errorsJob;
    
    @Autowired
    Job staxReaderJob;
    
    @RequestMapping("/job")
    public void handle() throws Exception {
        
        // Een unieke jobParameters maakt herstartbaar; JobInstance = Job + JobParameters
        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis())
                        .addString("infile", "gmuhand.gmu")
                        .addString("outfile", "file:target/test-outputs/output.txt").toJobParameters();
        
        jobLauncher.run(ioSampleJob, jobParameters);
    }

    @RequestMapping("/errorjob")
    public void process2() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(errorsJob, jobParameters);
    }

    @RequestMapping("/staxreaderjob")
    public void staxReaderJob() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(staxReaderJob, jobParameters);
    }

}

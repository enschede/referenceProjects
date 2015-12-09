package nl.marcenschede.springtest.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job ioSampleJob;
    
    @Autowired
    Job errorsJob;
    
    @Autowired
    Job staxWriterJob;
    
    @Autowired
    Job staxReaderJob;
    
    @Autowired
    Job staxCamtReaderJob;
    
    @Autowired
    Job staxPainWriterJob;

    @Autowired
    private PersonRepository personRepository;

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

    @RequestMapping("/staxwriterjob")
    public void staxWriterJob() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(staxWriterJob, jobParameters);
    }

    @RequestMapping("/staxreaderjob")
    public void staxReaderJob() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(staxReaderJob, jobParameters);
    }

    @RequestMapping("/camtreaderjob")
    public void camtReaderJob() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(staxCamtReaderJob, jobParameters);
    }

    @RequestMapping("/painwriterjob")
    public void painWriterJob() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("starttime", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(staxPainWriterJob, jobParameters);
    }

    @RequestMapping("/person")
    public @ResponseBody String person() throws Exception {

        Optional<Person> personOptional = personRepository.findById(1L);
        
        return personOptional.toString();
    }

}

package nl.marcenschede.springtest.error;

import nl.marcenschede.springtest.app.LogItem;
import nl.marcenschede.springtest.app.LogItemRepository;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by marc on 17/03/15.
 */
public class ErrorTestWriter implements ItemWriter<String> {

    @Autowired
    private LogItemRepository logItemRepository;

    @BeforeWrite
    public void beforeWrite(List<String> in) {
        System.out.println("ErrorTestWriter::beforeWrite, in=" + in);

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::beforeWrite" + in.toString()));
    }

    @AfterWrite
    public void afterWrite(List<String> in) {
        System.out.println("ErrorTestWriter::afterWrite, in=" + in);

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::afterWrite" + in.toString()));
    }

    @OnWriteError
    public void onWriteError(Exception e, List<String> in) {
        System.out.println("ErrorTestWriter::onWriteError, in=" + in + ", exception=" + e.getMessage());

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::onWriteError" + in.toString()));
    }

    @OnSkipInWrite
    public void onSkipInWrite(String in, Exception e) {
        System.out.println("ErrorTestWriter::onSkipInProcess, in=" + in + ", exception=" + e.getMessage());

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::onSkipInWrite" + in.toString()));
    }

    @BeforeJob
    public void beforeJob() {
        System.out.println("ErrorTestWriter::beforeJob");
    }

    @AfterJob
    public void afterJob() {
        System.out.println("ErrorTestWriter::afterJob");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("ErrorTestWriter::beforeStep");

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::beforeStep"));
    }

    @AfterStep
    public void afterStep() {
        System.out.println("ErrorTestWriter::afterStep");

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::afterStep"));
    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("ErrorTestWriter::write, geleverde items = " + items.toString());

        logItemRepository.save(new LogItem(-1, "ErrorTestWriter::write" + items.toString()));
        
        if(items.contains("26.00"))
            throw new NonFunctionException("Kan waar 26.00 niet afdrukken");
    }
}

package nl.marcenschede.springtest.simplejob.step1;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by marc on 08/11/14.
 */
public class MyItemWriter implements ItemWriter<String> {

    @BeforeStep
    public void beforeStep() {
        System.out.println("MyItemWriter::beforeStep()");
    }


    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("items.length=" + items.size());
        for(String item : items)
            System.out.println("item=" + item);
    }
}

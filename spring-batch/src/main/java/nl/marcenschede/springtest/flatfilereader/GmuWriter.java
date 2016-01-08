package nl.marcenschede.springtest.flatfilereader;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by marc on 08/11/14.
 */
public class GmuWriter implements ItemWriter<GmuVO> {

    @BeforeStep
    public void beforeStep() {
        System.out.println("MyItemWriter::beforeStep()");
    }

    @Override
    public void write(List<? extends GmuVO> items) throws Exception {
        System.out.println("items.length=" + items.size());
        for(GmuVO item : items)
            System.out.println("item.class=" + item.getClass().getSimpleName() + ", item=" + item.toString());
    }
}

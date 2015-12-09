package nl.marcenschede.springtest.step2;

import nl.marcenschede.springtest.step2.GmuVO;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by marc on 08/11/14.
 */
public class MyItemWriter2 implements ItemWriter<GmuVO> {

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

package nl.marcenschede.springtest.step1;

/**
 * Created by marc on 11/11/14.
 */
public class ItemProcessor implements org.springframework.batch.item.ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item + "!";
    }
}

package nl.marcenschede.springtest.step4;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 19/11/14.
 */
@Configuration
public class FlatFileWriter2 {
    
    @Bean
    @Scope("step")
    public FlatFileItemWriter itemWriter4(@Value("#{jobParameters[outfile]}") String pathToFile) {
        FlatFileItemWriter flatFileItemWriter = new FlatFileItemWriter();
        flatFileItemWriter.setLineAggregator(lineAggregator4());

        try {
            flatFileItemWriter.setResource(new UrlResource(pathToFile));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return flatFileItemWriter;
    }

    @Bean()
    public FixedLengthLineAggregator lineAggregator4() {
        FixedLengthLineAggregator fixedLengthLineAggregator = new FixedLengthLineAggregator();
        fixedLengthLineAggregator.setFormats(testformat());

        return fixedLengthLineAggregator;
    }

    @Bean
    public List<String> testformat() {
        List<String> result = new ArrayList<>();
        result.add("id,%-6s");
        result.add("voornaam,%-40s");
        result.add("achternaam,%-40s");
        result.add("adres.straat,%-40s");
        result.add("adres.huisnummer,%6s");

        return result;
    }
}

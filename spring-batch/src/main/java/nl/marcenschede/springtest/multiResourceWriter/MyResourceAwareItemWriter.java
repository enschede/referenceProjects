package nl.marcenschede.springtest.multiResourceWriter;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class MyResourceAwareItemWriter implements ResourceAwareItemWriterItemStream<String> {
    private Resource resource;
    private PrintStream ps;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("MyResourceAwareItemWriter::open");

        try {
            ps = new PrintStream(new FileOutputStream(resource.getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("MyResourceAwareItemWriter::update");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("MyResourceAwareItemWriter::close");

        ps.close();
    }

    @Override
    public void setResource(Resource resource) {
        System.out.println("MyResourceAwareItemWriter::setResource, resource = " + resource.toString());
        this.resource = resource;
    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("MyResourceAwareItemWriter::write");

        for(String item : items) {
            ps.printf("Item = [%s]\n", item);
            System.out.printf("Item = [%s]\n", item);
        }
    }
}

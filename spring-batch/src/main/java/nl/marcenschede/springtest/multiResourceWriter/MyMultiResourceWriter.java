package nl.marcenschede.springtest.multiResourceWriter;

import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.core.io.FileSystemResource;

public class MyMultiResourceWriter extends MultiResourceItemWriter<String> {

    public MyMultiResourceWriter() {
        super();
    }

    public void setFileName(String filename) {
        setResource(new FileSystemResource(filename));
    }

    @Override
    public void setItemCountLimitPerResource(int itemCountLimitPerResource) {
        super.setItemCountLimitPerResource(itemCountLimitPerResource);
        System.out.printf("MyMultiResourceWriter::setItemCountLimitPerResource, itemCountLimitPerResource = %d\n\n", itemCountLimitPerResource);
    }
}

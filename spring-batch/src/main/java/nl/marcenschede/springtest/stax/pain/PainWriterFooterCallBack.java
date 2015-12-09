package nl.marcenschede.springtest.stax.pain;

import org.springframework.batch.item.xml.StaxWriterCallback;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Created by marc on 01/04/15.
 */
public class PainWriterFooterCallBack implements StaxWriterCallback {

    @Override
    public void write(XMLEventWriter xmlEventWriter) throws IOException {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            xmlEventWriter.add(eventFactory.createEndElement(new QName("PmtInf"), null));

            xmlEventWriter.add(eventFactory.createEndElement(new QName("CstmrDrctDbtInitn"), null));

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}

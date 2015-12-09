package nl.marcenschede.springtest.stax.writer;

import org.springframework.batch.item.xml.StaxWriterCallback;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Created by marc on 01/04/15.
 */
public class UserXMLHeaderCallBack implements StaxWriterCallback {
    
    @Override
    public void write(XMLEventWriter xmlEventWriter) throws IOException {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            QName qName = new QName("d");
            xmlEventWriter.add(eventFactory.createStartElement(qName, null, null));
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}

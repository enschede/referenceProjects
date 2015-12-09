package nl.marcenschede.springtest.stax.pain;

import nl.marcenschede.springtest.stax.pain.xsd.GroupHeader39;
import org.springframework.batch.item.xml.StaxWriterCallback;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by marc on 01/04/15.
 */
public class PainWriterHeaderCallBack implements StaxWriterCallback {

    private XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    
    @Override
    public void write(XMLEventWriter xmlEventWriter) throws IOException {
        try {
            xmlEventWriter.add(eventFactory.createStartElement(new QName("CstmrDrctDbtInitn"), null, null));

            createHeader(xmlEventWriter);

            xmlEventWriter.add(eventFactory.createStartElement(new QName("PmtInf"), null, null));
                    
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void createHeader(XMLEventWriter xmlEventWriter)
            throws JAXBException {
        GroupHeader39 groupHeader39 = GroupHeader39Builder.with()
                .setXmlGregorianCalendar(LocalDateTime.of(2015, 5, 1, 0, 0))
                .setNm("Decisco")
                .build();

        JAXBContext jc = JAXBContext.newInstance(GroupHeader39.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        marshaller.marshal(groupHeader39, xmlEventWriter);
    }

}

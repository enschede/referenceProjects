package app;

import javax.xml.bind.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * /**
 * XML handlers for schemas without a XML root element
 * See camt and pain xsd's for examples
 */
public class RootlessXmlHandling {

    public static OutputStream mashalObject(Object messageForQueue)
            throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(messageForQueue.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(messageForQueue, outputStream);

        return outputStream;
    }

    public static Object unmarshalToObject(String messageFromQueue, Class clazz)
            throws JAXBException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(messageFromQueue.getBytes());
        return unmarshalToObject(inputStream, clazz);
    }

    public static Object unmarshalToObject(InputStream inputStream, Class clazz)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return ((JAXBElement)jaxbUnmarshaller.unmarshal(inputStream)).getValue();
    }

}

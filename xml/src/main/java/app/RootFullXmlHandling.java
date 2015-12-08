package app;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * XML handlers for schemas with a XML root element
 */
public class RootFullXmlHandling {

    public static String mashalObjectToString(Object messageForQueue)
            throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(messageForQueue.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(messageForQueue, outputStream);

        return outputStream.toString();
    }

    public static Object unmarshalStreamToObject(InputStream inputStream, Class clazz)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return jaxbUnmarshaller.unmarshal(inputStream);
    }

    public static Object unmarshalStringToObject(String messageFromQueue, Class clazz)
            throws JAXBException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(messageFromQueue.getBytes());
        return unmarshalStreamToObject(inputStream, clazz);
    }

}

package app;

import iso.std.iso._20022.tech.xsd.camt_053_001.Document;
import iso.std.iso._20022.tech.xsd.camt_053_001.ObjectFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertThat;

public class RootlessXmlHandlingTest {

    @Test
    public void shouldImportXmlData() throws JAXBException
    {
        // Given
        InputStream xmlData = this.getClass().getResourceAsStream("ING CAMT053 xml_example feb 2014.xml");

        // When
        Document document = (Document) RootlessXmlHandling.unmarshalToObject(xmlData, Document.class);

        // Then
        assertThat(document, CoreMatchers.notNullValue());
        assertThat(document.getBkToCstmrStmt(), CoreMatchers.notNullValue());
        assertThat(document.getBkToCstmrStmt().getStmt(), CoreMatchers.notNullValue());
    }

    @Test
    public void shouldExportXmlData() throws JAXBException {
        // Given
        InputStream xmlData = this.getClass().getResourceAsStream("ING CAMT053 xml_example feb 2014.xml");
        Document document = (Document) RootlessXmlHandling.unmarshalToObject(xmlData, Document.class);

        // When
        ObjectFactory objectFactory = new ObjectFactory();

        JAXBContext jaxbContext = JAXBContext.newInstance(document.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        JAXBElement<Document> documentJAXBElement = objectFactory.createDocument(document);
        jaxbMarshaller.marshal(documentJAXBElement, outputStream);

        String actualXmlData = outputStream.toString();

        // Then
        assertThat(actualXmlData, CoreMatchers.containsString("BkToCstmrStmt"));
    }

}
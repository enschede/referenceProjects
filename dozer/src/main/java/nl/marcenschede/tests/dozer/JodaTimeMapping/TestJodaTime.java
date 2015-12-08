package nl.marcenschede.tests.dozer.JodaTimeMapping;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by marc on 19/09/15.
 */
public class TestJodaTime {

    public void test() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = getMapper();

        Persoon persoon = new Persoon("Marc", "10-04-1980", "25-09-2015");
        SysPerson sysPerson = new SysPerson();

        mapper.map(persoon, sysPerson);

        System.out.println(sysPerson.toString());
    }

    private DozerBeanMapper getMapper() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = new DozerBeanMapper();

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Persoon.class, SysPerson.class)
                        .fields("naam", "voornaam")
                        .fields("geboortedatum", "geboortedatum", FieldsMappingOptions.customConverter(StringToLocalDateCustomConverter.class))
                        .fields("aanmelddatum", "aanmelding", FieldsMappingOptions.customConverter(StringToDateTimeCustomConverter.class));
            }
        };

        mapper.addMapping(builder);
        return mapper;
    }
}

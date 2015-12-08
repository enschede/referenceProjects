package nl.marcenschede.tests.dozer.SettersAndGetters;

import nl.marcenschede.tests.dozer.Persoon;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldDefinition;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by marc on 19/09/15.
 */
public class TestMap4 {

    public void test() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = getMapper();

        Persoon persoon = new Persoon("Marc", "Enschede");
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
                        .fields("plaats", new FieldDefinition("address").setMethod("setCity"));
            }
        };

        mapper.addMapping(builder);
        return mapper;
    }
}

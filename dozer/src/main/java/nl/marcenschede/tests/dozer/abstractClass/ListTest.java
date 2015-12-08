package nl.marcenschede.tests.dozer.abstractClass;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class ListTest {
    
    public void test() throws FileNotFoundException, URISyntaxException {
        DozerBeanMapper mapper = getMapper();
        Person source = new Person();
        source.setName("Marc");
        source.setVoorkeuren(Arrays.asList(new BierVoorkeur("Erdinger"), new WijnVoorkeur("Chateau Musar")));
        
        Person destination = new Person();
        
        mapper.map(source, destination);
        
        System.out.println(destination);
    }

    private DozerBeanMapper getMapper() throws URISyntaxException, FileNotFoundException {
        final DozerBeanMapper mapper = new DozerBeanMapper();

        URL dozerdef = this.getClass().getResource("dozerList.xml");
        File dozerdeffile = new File(dozerdef.toURI());

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Person.class, Person.class, TypeMappingOptions.wildcard(true))
                        .fields("voorkeuren", "voorkeuren", 
                                FieldsMappingOptions.hintA(BierVoorkeur.class, WijnVoorkeur.class),
                                FieldsMappingOptions.hintB(BierVoorkeur.class, WijnVoorkeur.class));
                        
            }
        };
        
        mapper.addMapping(builder);

        return mapper;
    }
}

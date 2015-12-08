package nl.marcenschede.tests.dozer.map2;

import nl.marcenschede.tests.dozer.Persoon;
import org.dozer.DozerBeanMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 19/09/15.
 */
public class TestMap2 {
    
    public void test() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = getMapper();

        Persoon persoon = new Persoon("Marc", "Enschede");
        SysPerson sysPerson = new SysPerson();
        
        mapper.map(persoon, sysPerson);
        
        System.out.println(sysPerson.toString());        
    }
 
    private DozerBeanMapper getMapper() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = new DozerBeanMapper();

        URL dozerdef = this.getClass().getResource("dozer2.xml");
        File dozerdeffile = new File(dozerdef.toURI());
        
        mapper.addMapping(new FileInputStream(dozerdeffile));
        
        return mapper;
    }
}

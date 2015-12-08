package nl.marcenschede.tests.dozer.map1;

import nl.marcenschede.tests.dozer.Persoon;
import org.dozer.DozerBeanMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestMap1 {
    
    public void test() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = getMapper();

        Persoon persoon = new Persoon("Marc", "Enschede");
        Map<String, String> output = new HashMap<String, String>();
        
        mapper.map(persoon, output);
        
        System.out.println(output.toString());        
    }
 
    private DozerBeanMapper getMapper() throws URISyntaxException, FileNotFoundException {
        DozerBeanMapper mapper = new DozerBeanMapper();

        URL dozerdef = this.getClass().getResource("dozer.xml");
        File dozerdeffile = new File(dozerdef.toURI());
        
        mapper.addMapping(new FileInputStream(dozerdeffile));
        
        return mapper;
    }
}

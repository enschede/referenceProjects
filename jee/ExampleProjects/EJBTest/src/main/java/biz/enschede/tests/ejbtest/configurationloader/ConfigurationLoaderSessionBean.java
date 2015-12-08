/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.enschede.tests.ejbtest.configurationloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * This class shows how to load a configuration file from the package.
 * @author marc
 */
@Singleton
@Startup
public class ConfigurationLoaderSessionBean {

    private static final Logger LOG = Logger.getLogger(ConfigurationLoaderSessionBean.class.getName());

    @PostConstruct
    public void init() {
        
        // Resource to load should be in src/main/webapp/WEB-INF/classes directory
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream res = classLoader.getResourceAsStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(res));
        
        String line;

        try {
            while ((line = br.readLine()) != null) {
                LOG.log(Level.INFO, "Buffer text = {0}", line);
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        // Add business logic below. (Right-click in editor and choose
        // "Insert Code > Add Business Method")
    }
}

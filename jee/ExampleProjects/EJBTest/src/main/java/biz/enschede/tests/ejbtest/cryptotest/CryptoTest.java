/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.enschede.tests.ejbtest.cryptotest;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author marc
 */
@Singleton
@Startup
@DependsOn("ConfigurationLoaderSessionBean")
public class CryptoTest {
    private static final Logger LOG = Logger.getLogger(CryptoTest.class.getName());

    @PostConstruct
    public void init() {
        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testcert.p12");
            keystore.load(is, "12345".toCharArray());
            
            // Show key aliasses
            List<String> keys = Collections.list(keystore.aliases());            
            for(String alias : keys)
                LOG.log(Level.INFO, "CryptoTest::init::alias={0}", alias);
                
            
            Certificate certificate = keystore.getCertificate(keys.get(0));
            LOG.log(Level.INFO, "CryptoTest::init::certificate.getPublicKey().getAlgorithm()={0}", certificate.getPublicKey().getAlgorithm());
            
            PrivateKey privatekey = (PrivateKey)keystore.getKey(keys.get(0), "12345".toCharArray());
            LOG.log(Level.INFO, "CryptoTest::init::privatekey.getAlgorithm={0}", privatekey.getAlgorithm());
            LOG.log(Level.INFO, "CryptoTest::init::privatekey.getEncoded={0}", privatekey.getEncoded());
            
        } catch (KeyStoreException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(CryptoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

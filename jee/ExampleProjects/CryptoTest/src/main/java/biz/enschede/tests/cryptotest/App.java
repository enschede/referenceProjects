package biz.enschede.tests.cryptotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public KeyStore getKeystore() {

        File file = new File("src/main/resources/testcert.p12");

        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(file), "12345".toCharArray());

            List<String> aliasses = Collections.list(ks.aliases());
            for (String alias : aliasses) {
                LOG.log(Level.INFO, "Alias={0}", alias);
            }
        } catch (KeyStoreException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ks;
    }

    public void secureRandom(KeyStore ks) {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(10L);
            byte[] random = sr.generateSeed(5);
            LOG.log(Level.INFO, "App::secureRandom::randon.length={0}", random.length);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void messageDigest() {
        try {
            File file = new File("src/main/resources/input.txt");
            InputStream is = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(is);

            MessageDigest md1 = MessageDigest.getInstance("SHA-1");

            md1.update(data);
            byte[] digest = md1.digest();

            LOG.log(Level.INFO, "App::messageDigest::digest.length is {0} bytes", digest.length);
            LOG.log(Level.INFO, "App::messageDigest::digest(hex) = {0}", byteToHex(digest));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create and verify a signature using RSA-keypair
     *
     * @param ks
     */
    public void signature(KeyStore ks) {
        try {
            File file = new File("src/main/resources/input.txt");
            InputStream is = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(is);

            Signature signer = Signature.getInstance("MD5withRSA");

            Key privateKey1 = ks.getKey(ks.aliases().nextElement(), "12345".toCharArray());
            signer.initSign((PrivateKey) privateKey1);

            signer.update(data);
            byte[] sign = signer.sign();

            Signature verifier = Signature.getInstance("MD5withRSA");

            Certificate certificate = ks.getCertificate(ks.aliases().nextElement());
            verifier.initVerify(certificate);

            verifier.update(data);
            LOG.log(Level.INFO, "Signer verification result = {0}", verifier.verify(sign));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cipher and decipher data with AES using a random session key
     *
     * @param ks
     */
    public void cipher(KeyStore ks) {
        // Uit voorbeeld
        // http://stackoverflow.com/questions/6486121/aes-encryption-in-java?rq=1

        try {
            File file = new File("src/main/resources/input.txt");
            InputStream is = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(is);

            // Create session key
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(new SecureRandom());
            SecretKey skey = kgen.generateKey();
            byte[] rawkey = skey.getEncoded();

            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");

            // Create and run cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] ciphertext = cipher.doFinal(data);
            LOG.log(Level.INFO, "Cipher encrypted data lengthe is {0}", ciphertext.length);
            LOG.log(Level.INFO, "Cipher encrypted data is {0}", byteToHex(ciphertext));

            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(rawkey);

            LOG.log(Level.INFO, "Cipher encrypted data (base64) is {0}", base64);

            // Create and run decipher
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = decipher.doFinal(ciphertext);

            // Create String from byte[]
            // String result = new String(decrypted);
            LOG.log(Level.INFO, "Cipher decrypted validation result is {0}", isEqual(data, decrypted));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Zelfde als cipher, echter nu met CipherInputStream.
     * 
     * @param ks
     */
    public void cipher2(KeyStore ks) {
        // Uit voorbeeld
        // http://stackoverflow.com/questions/6486121/aes-encryption-in-java?rq=1

        try {
            // Open filestream
            File file = new File("src/main/resources/input.txt");
            InputStream is = new FileInputStream(file);

            OutputStream os = new FileOutputStream(new File("/dev/null"));
            
            // Create session key
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(new SecureRandom());
            SecretKey skey = kgen.generateKey();
            byte[] rawkey = skey.getEncoded();

            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");

            // Create and run cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            CipherInputStream cis = new CipherInputStream(is, cipher);
//            byte[] ciphertext = cipher.doFinal(data);

            byte[] ciphertext = new byte[512];
            BASE64Encoder encoder = new BASE64Encoder();
            int l;
            while ((l = cis.read(ciphertext)) != -1) {
                
                // Hier komen korte losse stukken uit, vraag is hoe dat goed te combineren.
                // Er komen nu de volgende blokken uit 512, 512, 216, 16.
                
                LOG.log(Level.INFO, "Block read len = {0}", l);
                
                LOG.log(Level.INFO, "Cipher encrypted data length is {0}", Arrays.copyOf(ciphertext, l).length);
                LOG.log(Level.INFO, "Cipher encrypted data (base64) is {0}", byteToHex(Arrays.copyOf(ciphertext, l)));
                LOG.log(Level.INFO, "Cipher encrypted data (base64) is {0}", encoder.encode(Arrays.copyOf(ciphertext, l)));
                
                os.write(ciphertext);
            }

            is.close();
            os.close();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void keyStoreManipulating(KeyStore ks) {
        try {
            Certificate cert = ks.getCertificate(ks.aliases().nextElement());
            Certificate[] certChain = ks.getCertificateChain(ks.aliases().nextElement());
            Key key = ks.getKey(ks.aliases().nextElement(), "12345".toCharArray());

            LOG.log(Level.INFO, "Certificate encoded = {0}", byteToHex(cert.getEncoded()));
            LOG.log(Level.INFO, "Certificate public key algorithm = {0}", cert.getPublicKey().getAlgorithm());
            LOG.log(Level.INFO, "Certificate public key format = {0}", cert.getPublicKey().getFormat());
            LOG.log(Level.INFO, "Certificate public key encoded = {0}", byteToHex(cert.getPublicKey().getEncoded()));
            LOG.log(Level.INFO, "Certificate public key length = {0}", cert.getPublicKey().getEncoded().length);
            LOG.log(Level.INFO, "Certificate private key algorithm = {0}", key.getAlgorithm());
            LOG.log(Level.INFO, "Certificate private key format = {0}", key.getFormat());
            LOG.log(Level.INFO, "Certificate private key encoded = {0}", byteToHex(key.getEncoded()));
            LOG.log(Level.INFO, "Certificate private key length = {0}", key.getEncoded().length);

        } catch (KeyStoreException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateEncodingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crypt and decrypt AES key with RSA keypair See
     * http://stackoverflow.com/questions/9658921/encrypting-aes-key-with-rsa-public-key
     *
     * @param ks
     */
    private void keyCipher2(KeyStore ks) {
        try {
            // Create session key for AES encoding
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(new SecureRandom(new byte[]{0}));
            SecretKey originalKey = kgen.generateKey();
            LOG.log(Level.INFO, "App::keyCipher2::originalKey={0}", byteToHex(originalKey.getEncoded()));

            // Get certificate from keystore
            Certificate cert = ks.getCertificate(ks.aliases().nextElement());

            // initialize the cipher with the user's public key
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, cert.getPublicKey());
            byte[] encryptedKey = cipher.doFinal(originalKey.getEncoded());

//            LOG.log(Level.INFO, "App::keyCipher2::encryptedKey={0}", byteToHex(encryptedKey));
            LOG.log(Level.INFO, "App::keyCipher2::encryptedKey.length={0}", encryptedKey.length);

            // this is OUR private key
            PrivateKey privKey = (PrivateKey) ks.getKey(ks.aliases().nextElement(), "12345".toCharArray());

            // initialize the decipher...
            Cipher decipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            decipher.init(Cipher.DECRYPT_MODE, privKey);

            // generate the aes key!
            SecretKeySpec decryptedKey = new SecretKeySpec(decipher.doFinal(encryptedKey), "AES");

            LOG.log(Level.INFO, "App::keyCipher2::decryptedKey={0}", byteToHex(decryptedKey.getEncoded()));
            LOG.log(Level.INFO, "App::keyCipher2::verify={0}", isEqual(originalKey.getEncoded(), decryptedKey.getEncoded()));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        KeyStore ks = app.getKeystore();

        app.secureRandom(ks);
        app.messageDigest();
        app.signature(ks);
        app.cipher(ks);
        app.cipher2(ks);
        app.keyStoreManipulating(ks);
        app.keyCipher2(ks);

    }

    private static String byteToHex(byte[] ba) {

        StringBuilder sb = new StringBuilder(ba.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : ba) {
            formatter.format("%02x", b);
        }

        return sb.toString();
    }

    /**
     * Vergelijk 2 byte arrays. Deze functie zit al in Arrays class
     * 
     * @param a
     * @param b
     * @return 
     */
    private static boolean isEqual(byte[] a, byte[] b) {
        return Arrays.equals(a, b);
    }
}

package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class KeyStoreStorage {
	private static final String KEYSTOREPW = "cipherkeystore";
	private static final String KEYSTOREFILENAME = "cipherks.keystore";
	private static final String ENTRYNAME= "cipherentry";
	private static final String KEYSTORETYPE = "JCEKS";
	
	
	/**
	 * Example
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//CREATE KEYSTORE
		KeyStore keyStore = createKeyStore(KEYSTOREFILENAME, KEYSTOREPW);

		//SAVE KEYSTORE WITH CIPHER
        byte[] cipherKey = "kjas8d73jf".getBytes();
        PasswordProtection keyPassword = storeKey(cipherKey, keyStore, KEYSTOREFILENAME);
        
        //RETRIEVE KEY
        System.out.println(retrieveKey(keyStore, keyPassword));
    }
    
	/**
	 * Create KeyStore
	 * @param fileName
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	private static KeyStore createKeyStore(String fileName, String pass) throws Exception {
		File file = new File(fileName);
		final KeyStore keyStore = KeyStore.getInstance(KEYSTORETYPE);
		/** Load KeyStore or Create one if it doesnt exist**/
		if (file.exists()) {
			keyStore.load(new FileInputStream(file), pass.toCharArray());
		} else {
			keyStore.load(null, null);
			keyStore.store(new FileOutputStream(fileName), pass.toCharArray());
		}
		return keyStore;
	}

	/**
	 * Store
	 * @param key
	 * @param keyStore
	 * @param keyStoreFilename
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static PasswordProtection storeKey(byte[] key, KeyStore keyStore, String keyStoreFilename) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
        SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "AES");
        KeyStore.SecretKeyEntry keyStoreEntry = new KeyStore.SecretKeyEntry(secretKey);
        PasswordProtection keyPassword = new PasswordProtection("pw-secret".toCharArray());
        keyStore.setEntry(ENTRYNAME, keyStoreEntry, keyPassword);
        keyStore.store(new FileOutputStream(keyStoreFilename), KEYSTOREPW.toCharArray());
        return keyPassword;
    }
    
	/**
	 * Retrieve Key
	 * @param keyStore
	 * @param keyPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableEntryException
	 * @throws KeyStoreException
	 */
    private static String retrieveKey(KeyStore keyStore, PasswordProtection keyPassword) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
    	KeyStore.Entry entry = keyStore.getEntry(ENTRYNAME, keyPassword);
    	SecretKey keyFound = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
    	return new String(keyFound.getEncoded());
    }

}

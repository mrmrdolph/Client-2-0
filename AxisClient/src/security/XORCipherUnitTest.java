package security;
import static org.junit.Assert.*;

import org.junit.Test;


public class XORCipherUnitTest {

	
	private String key = "auz94hhgf98";
	
	@Test
	public void testCipher() {
		String message = "This message should be handled secretly.";
		/**
		 * Encrypt
		 */
		byte [] secret = XORCipher.crypt(message.getBytes(), key.getBytes());
		System.out.println("Secret:\n" + new String(secret));
		
		/**
		 * Decrypt
		 */
		byte [] decrypted = XORCipher.crypt(secret, key.getBytes());
		System.out.println("Decrypted:\n" + new String(decrypted));
		
		/**
		 * Compare result
		 */
		assertEquals(message, new String(decrypted));
	}

}

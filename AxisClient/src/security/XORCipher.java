package security;
import javax.crypto.SecretKey;


public class XORCipher {
	
//	You have the right to remain silent, everything you say can and will be used against you in the court of law.
	
	/**
	 * Example of usage
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "This message should be handled secretly.";
		String key = "abcdef12345";
		byte[] encrypted = crypt(text.getBytes(), key.getBytes());
		byte[] decrypted = crypt(encrypted, key.getBytes());
		
		String a = new String(encrypted);
		String b = new String(decrypted);
		
		System.out.println("crypted message:\n"+a);
		System.out.println("decrypted message:\n"+b);
		
		byte[] cString = {(byte)0x25, (byte)0x03, (byte)0x15, (byte)0x0D, (byte)0x01};
		
		byte[] res = crypt(cString, key.getBytes());
		System.out.println("Byte encrypted: "+new String(res));
		
	}
	
	/**
	 * Used to encrypt and decrypt
	 * @param text
	 * @param key
	 * @return
	 */
	public static byte[] crypt(byte[] text, byte[] key) {
		if (key.length == 0) return null;
		byte[] n = new byte[text.length];
		for(int i = 0; i < text.length; i += 1) 
			n[i] = (byte)((int)text[i]^(int)key[i%key.length]);
		return n;
	}
	

}

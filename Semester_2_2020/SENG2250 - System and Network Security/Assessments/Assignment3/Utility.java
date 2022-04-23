import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utility 
{
	private static BigInteger zero = new BigInteger("0");
	private static BigInteger one = new BigInteger("1");
	private static BigInteger two = new BigInteger("2");
	private final static BigInteger IPAD = new BigInteger(new byte[] {0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c, 0x5c});
	private final static BigInteger OPAD = new BigInteger(new byte[] {0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36, 0x36});

	
	public static BigInteger RSA(BigInteger phi, BigInteger rsaPublicKey)
	{
		BigInteger rsaKey = rsaPublicKey.modInverse(phi);
		
		return rsaKey;
	}

	// Return array for steps of the EEA
	public static BigInteger GCD(BigInteger p, BigInteger q) 
	{
		BigInteger gcd = new BigInteger("1");
		
        for(BigInteger i = BigInteger.ONE; ((i.compareTo(p) == -1) && (i.compareTo(q) == -1)); i = i.add(BigInteger.ONE))
        {
            if(p.mod(i).compareTo(BigInteger.ZERO) == 0 && q.mod(i).compareTo(BigInteger.ZERO) == 0)
                gcd = i;
        }
        
        return gcd;
	}

	public static BigInteger FastExponentation(BigInteger base, BigInteger exponent, BigInteger modulus) 
	{
		// given x^y, where y = 1.
		if (modulus.equals(one))
			return base;

		BigInteger result = BigInteger.ONE;

		while (exponent.compareTo(zero) == 1) 
		{
			int r = 3;
			if (exponent.testBit(1))
				result = result.multiply(base).mod(modulus);
			exponent = exponent.shiftRight(2);
			base = base.multiply(base).mod(modulus);
		}

		return result;
	}

	// run the SHA-256 hash function
	public static String GetSha256(String value) 
	{
		try 
		{
			MessageDigest message = MessageDigest.getInstance("SHA-256");
			message.update(value.getBytes());
			return BytesToHex(message.digest());
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException(ex);
		}
	}

	// convert a byte array to hexadecimal
	private static String BytesToHex(byte[] byteString) 
	{
		StringBuffer result = new StringBuffer();
		for (byte b : byteString)
			result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		
		return result.toString();
	}

	// Generate an RSA signature
	public static String BuildSignature(String message, BigInteger privateKey, BigInteger publicKey)
	{
		String signature = GetSha256(message);
		BigInteger intSignature = FastExponentation(new BigInteger(signature, 16), privateKey, publicKey);
		signature = intSignature.toString(16);
		return signature;
	}
	
	// Generate a random 1024-bit probable prime number
	public static BigInteger GeneratePrime(int bitSize) 
	{
		boolean primeFound = false;

		BigInteger primeCandidate = new BigInteger(1024, new Random());

		do 
		{
			primeCandidate = new BigInteger(1024, new Random());
			if (primeCandidate.isProbablePrime(12)) 
			{
				primeFound = true;
			}
		} 
		while (!primeFound);

		return primeCandidate;
	}

	// Cipher block chaining for encrypting plaintext.
	public static String CBCEncryption(String message, String privateKey, String iv) throws Exception
	{
		String[] messageSubstrings = new String[4];
		
		// split plaintext into 4 even sections.
		for (int i = 0; i < 4; i++)
		{
			messageSubstrings[i] = message.substring(i * 32, (i+1)*32);
		}
		
		String cipher = iv;
		String cipherData = "";
		byte[] privateBytes = HexToByteArray(privateKey);
		
		for (int i = 0; i < 4; i++) 
		{
			byte[] cipherBytes = HexToByteArray(messageSubstrings[i]);
					
			Cipher encryption = Cipher.getInstance("AES/CBC/NOPADDING");
			byte[] ivBytes = HexToByteArray(cipher);
			
			SecretKey aesKey = new SecretKeySpec(privateBytes, "AES");
			
			encryption.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(ivBytes));
			
	        byte[] cipherText = encryption.update(cipherBytes);
	      
	        cipher = BytesToHex(cipherText);
	        cipherData += cipher;
		}
		
		return cipherData;
	}

	// Cipher block chaining for decrypting plaintext.
	public static String CBCDecryption(String message, String privateKey, String iv) throws Exception 
	{
		String[] messageSubstrings = new String[4];

		// split ciphertext into 4 even sections.
		for (int i = 0; i < 4; i++)
		{
			messageSubstrings[i] = message.substring(i * 32, (i+1)*32);
		}
		
		String decipher = iv;
		String deciphered = "";
		byte[] privateBytes = HexToByteArray(privateKey);
		
		for (int i = 0; i < 4; i++) 
		{	
			if (i > 0)
			{
				decipher = messageSubstrings[i - 1];
			}
			byte[] ivBytes = Utility.HexToByteArray(decipher);
			byte[] decipherBytes = HexToByteArray(messageSubstrings[i]);
			Cipher decryption = Cipher.getInstance("AES/CBC/NOPADDING");
			
			SecretKey aesKey = new SecretKeySpec(privateBytes, "AES");
			
			decryption.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(ivBytes));
			
	        byte[] decipherText = decryption.update(decipherBytes);
	        deciphered += new String(decipherText, StandardCharsets.UTF_8);
		}
		
		return deciphered;
	}

	// Hashed Message Authentication Code for user integrity
	public static String HMAC(String encryptedKey, String message) 
	{
		char charArray[] = new char[encryptedKey.length()];
		BigInteger intKey = new BigInteger(encryptedKey, 16);
		BigInteger messageKey = new BigInteger(message, 16);

		return GetSha256(intKey.xor(OPAD).toString() + GetSha256(intKey.xor(IPAD).toString() + message));
	}

	// Perform an XOR operation on 2 strings.
	public static String XOR(String x, String y) 
	{
		String result = "";

		for (int i = 0; i < x.length(); i++) 
		{
			result += ToHex(FromHex(x.charAt(i)) ^ FromHex(y.charAt(i)));
		}

		return result;
	}

	// Convert a regular string to hexadecimal.
	public static String StringToHex(String input) 
	{
		return String.format("%040x", new BigInteger(1, input.getBytes()));
	}
	
	// value must be an even-length string.
	public static byte[] HexToByteArray(String value) {
	    int length = value.length();
	    byte[] data = new byte[length / 2];
	    
	    for (int i = 0; i < length; i += 2) 
	    {
	        data[i / 2] = (byte) ((Character.digit(value.charAt(i), 16) << 4) + Character.digit(value.charAt(i+1), 16));
	    }
	    
	    return data;
	}

	// Generate a 16-byte initialisation vector.
	public static String GenerateIV() 
	{
		String hexIV = "";
		Random rand = new Random();
		for (int i = 0; i < 4; i++) 
		{
			hexIV += Integer.toHexString(rand.nextInt());
		}

		return hexIV;
	}
	
	private static int FromHex(char value) 
	{
	    if (value >= '0' && value <= '9') 
	    {
	        return value - '0';
	    }
	    if (value >= 'a' && value <= 'f') 
	    {
	        return value - 'a' + 10;
	    }
	    throw new IllegalArgumentException();
	}

	private static char ToHex(int value) 
	{
	    return "0123456789abcdef".charAt(value);
	}
}

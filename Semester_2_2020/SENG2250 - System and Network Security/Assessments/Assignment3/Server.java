import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class Server 
{
	// Unique ID of the client.
	private String clientID;

	// Unique ID of the server.
	private String serverID;

	// Unique ID of the session.
	private String sessionID;

	// Current initialisation vector for CBC
	private String currentIV;

	// Message shared by server
	private ServerMessage serverMessage;
	
	// shared public key
	private BigInteger rsaPublicKey = new BigInteger("65537");
	
	// generated private key for 1st Diffie-Hellman exchange
	private BigInteger[] serverPrivateKey;
	
	// generated client public key
	private BigInteger serverPublicKey;
	
	// generated server public key
	private BigInteger clientPublicKey;
	
	// Diffie-Hellman parameter 1
	private BigInteger diffieP;
	
	// Diffie-Hellman parameter 2
	private BigInteger diffieG;
	
	// random private number for Diffie-Hellman
	private BigInteger privateDHKey;
	
	// Diffie-Hellman client key
	private BigInteger clientDHKey;
	
	// Diffie-Hellman server key
	private BigInteger serverDHKey;
	
	// Diffie-Hellman combined key
	private BigInteger combinedDHKey;
	
	// Hashed Diffie-Hellman combined key
	private String encryptedDHKey;
	
	public Server()
	{
		diffieP = new BigInteger("178011905478542266528237562450159990145232156369120674273274450314442865788737020770612695252123463079567156784778466449970650770920727857050009668388144034129745221171818506047231150039301079959358067395348717066319802262019714966524135060945913707594956514672855690606794135837542707371727429551343320695239");
		diffieG = new BigInteger("174068207532402095185811980123523436538604490794561350978495831040599953488455823147851597408940950725307797094915759492368300574252438761037084473467180148876118103083043754985190983472601550494691329488083395492313850000361646482644608492304078721818959999056496097769368017749273708962006689187956744210730");
		serverID = "i00xjmaj99w2";
		sessionID = "3280723325235";
	}
	
	public void ReceiveConnection(ClientMessage clientMessage)
	{
		System.out.println("Server receives: " + clientMessage.ReadMessage());
	}
	
	
	public ServerMessage SendPublicKey()
	{
		System.out.println("Server shares public key: " + rsaPublicKey);
		return new ServerMessage(rsaPublicKey);
	}
	
	
	public ServerMessage SendHello()
	{
		System.out.println("Server sends server ID: " + serverID);
		System.out.println("Server receives session ID: " + sessionID);
		
		return new ServerMessage(new String[] {serverID, sessionID});
	}
	
	
	public void ReceiveHello(ClientMessage clientMessage)
	{
		clientID = clientMessage.ReadMessage();
		System.out.println("Server receives client ID: " + clientID);
	}	
	
	// send RSA signature
	public ServerMessage SendRSASignature()
	{
		// generate large primes for RSA key		
		BigInteger rsaPrimeP = Utility.GeneratePrime(1024);
		BigInteger rsaPrimeQ = Utility.GeneratePrime(1024);

		// privateKey = (random number A - 1) * (random number B - 1), where 0 < privateKey < prime
		BigInteger phi = (rsaPrimeP.subtract(BigInteger.ONE)).multiply(rsaPrimeQ.subtract(BigInteger.ONE));
		
		// pair public RSA key with 2048-bit prime
		BigInteger[] publicKeyPair = new BigInteger[2];
		publicKeyPair[0] = rsaPublicKey;
		publicKeyPair[1] = rsaPrimeP.multiply(rsaPrimeQ);

		BigInteger[] privateKeyTrio = new BigInteger[3];
		privateKeyTrio[0] = rsaPrimeP;
		privateKeyTrio[1] = rsaPrimeQ;
		privateKeyTrio[2] = Utility.RSA(phi, rsaPublicKey);

		String signature = Utility.BuildSignature("Hello", privateKeyTrio[2], publicKeyPair[1]);
		
		serverMessage = new ServerMessage(signature);
		
		serverMessage.AddKeyPair(publicKeyPair);
		serverMessage.AddKeyTrio(privateKeyTrio);
		
		System.out.println("Server generates and sends RSA signature: " + signature);
		return serverMessage;
	}
	
	// receive and verify RSA signature
	public void ReceiveRSASignature(ClientMessage message)
	{
		
	}

	// Send a public key to the client as per the Diffie-Hellman Key Agreement	
	public ServerMessage SendDHKey()
	{
		// generate positive random prime smaller than diffieP
		privateDHKey = Utility.GeneratePrime(1024);
		currentIV = Utility.GenerateIV();
		
		while (privateDHKey.compareTo(diffieP) == 1 && privateDHKey.compareTo(new BigInteger("2")) < 1)
		{
			privateDHKey = Utility.GeneratePrime(1024);
		}
		
		serverDHKey = Utility.FastExponentation(diffieG, privateDHKey, diffieP);
		
		System.out.println("Server sends Diffie-Hellman server public key: " + serverDHKey);
		System.out.println("Server sends initiation vector: " + currentIV);
		serverMessage = new ServerMessage(currentIV, serverDHKey);
		return serverMessage;
	}

	// Send a public key from the client as per the Diffie-Hellman Key Agreement		
	public void ReceiveDHKey(ClientMessage message)
	{
		clientDHKey = message.ReadKey();
		System.out.println("Server receives Diffie-Hellman client public key: " + clientDHKey);
	}
	
	public void CombineAndEncryptDHKey()
	{
		combinedDHKey = Utility.FastExponentation(clientDHKey, privateDHKey, diffieP);
		encryptedDHKey = Utility.GetSha256(combinedDHKey.toString());
		System.out.println("Server combines and encrypts Diffie-Hellman public keys: " + encryptedDHKey);
	}
	
	public ServerMessage SendEncryptedMessage() throws Exception
	{
		// Encrypt message with CBC
		String cbcMessage = ("Servers are superior to a client, who should simply stop trying.");
		System.out.println("Server encrypts a message: " + cbcMessage);
		
		cbcMessage = Utility.CBCEncryption(Utility.StringToHex(cbcMessage), encryptedDHKey, currentIV);
		System.out.println("Server sends a message to client: " + cbcMessage);
		// Integrity with HMAC
		String hmacMessage = Utility.HMAC(encryptedDHKey, cbcMessage);
		
		return new ServerMessage(new String[]{cbcMessage, hmacMessage});
	}
	
	public String ReceiveEncryptedMessage(ClientMessage serverMessage) throws Exception
	{
		String[] message = serverMessage.ReadStringArray();
		System.out.println("Server receives a message: " + message[0]);
		
		// Decrypt message with CBC
		String decryptMessage = Utility.CBCDecryption(message[0], encryptedDHKey, currentIV);
		System.out.println("Server decrypts a message: " + decryptMessage);
		
		// Integrity with HMAC
		// decryptMessage = Utility.HMAC(encryptedDHKey, decryptMessage);
		
		return new ServerMessage(decryptMessage).ReadMessage();
	}
}

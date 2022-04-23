import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Client 
{
	// Unique ID of the client.
	private String clientID;

	// Unique ID of the server.
	private String serverID;

	// Unique ID of the session.
	private String sessionID;

	// Current initialisation vector for CBC
	private String currentIV;
	
	// Message shared by client
	private ClientMessage clientMessage;
	
	// shared public key
	private BigInteger rsaPublicKey;
	
	// generated private key for 1st Diffie-Hellman exchange
	private BigInteger[] clientPrivateKey;
	
	// generated client public key
	private String clientPublicKey;
	
	// generated server public key
	private String serverPublicKey;
	
	// Diffie-Hellman parameter 1
	private BigInteger diffieP;
	
	// Diffie-Hellman parameter 2
	private BigInteger diffieG;
	
	private BigInteger privateDHKey;
	
	// Diffie-Hellman client key
	private BigInteger clientDHKey;
	
	// Diffie-Hellman server key
	private BigInteger serverDHKey;
	
	// Diffie-Hellman combined key
	private BigInteger combinedDHKey;

	// Hashed Diffie-Hellman combined key
	private String encryptedDHKey;
	
	public Client()
	{
		diffieP = new BigInteger("178011905478542266528237562450159990145232156369120674273274450314442865788737020770612695252123463079567156784778466449970650770920727857050009668388144034129745221171818506047231150039301079959358067395348717066319802262019714966524135060945913707594956514672855690606794135837542707371727429551343320695239");
		diffieG = new BigInteger("174068207532402095185811980123523436538604490794561350978495831040599953488455823147851597408940950725307797094915759492368300574252438761037084473467180148876118103083043754985190983472601550494691329488083395492313850000361646482644608492304078721818959999056496097769368017749273708962006689187956744210730");
		clientID = "fa92naoc9amma";
	}
	
	// Send a connection request to the server
	public ClientMessage ConnectToServer()
	{
		clientMessage = new ClientMessage("Hello.");
		System.out.println("Client sends: " + clientMessage.ReadMessage());
		return clientMessage;
	}

	// Receive the public key sent by the server
	public void ReceivePublicKey(ServerMessage publicKey)
	{
		try
		{
			rsaPublicKey = publicKey.ReadKey();
			System.out.println("Client acknowledges public key: " + rsaPublicKey);
		}
		catch (Exception e)
		{
			System.out.println("Error: Connection not established.");
		}
	}
	
	// 
	public ClientMessage SendHello()
	{
		System.out.println("Client sends client ID: " + clientID);
		return new ClientMessage(clientID);
	}
	
	//
	public void ReceiveHello(ServerMessage serverMessage)
	{
		serverID = serverMessage.ReadStringArray()[0];
		sessionID = serverMessage.ReadStringArray()[1];
		
		System.out.println("Client receives server ID: " + serverID);
		System.out.println("Client receives session ID: " + sessionID);
	}
	
	// send RSA signature
	public ClientMessage SendRSASignature()
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

		String signature = Utility.BuildSignature("test", privateKeyTrio[2], rsaPublicKey);
		
		clientMessage = new ClientMessage(signature);
		clientMessage.AddKeyPair(publicKeyPair);
		clientMessage.AddKeyTrio(privateKeyTrio);
		
		System.out.println("\nClient builds public key");
		
		return clientMessage;
	}
	
	// receive and verify RSA signature
	public void ReceiveRSASignature(ServerMessage message)
	{
		String hash = Utility.GetSha256("Hello");
		BigInteger intSignature = Utility.FastExponentation(new BigInteger(message.ReadMessage(), 16), message.ReadKeyPair()[0], message.ReadKeyPair()[1]);
		String signature = intSignature.toString(16);
		System.out.println("Signature verified: " + (hash != signature));
		
	}
	
	// Send a public key to the server as per the Diffie-Hellman Key Agreement	
	public ClientMessage SendDHKey()
	{
		// generate positive random prime smaller than diffieP
		privateDHKey = Utility.GeneratePrime(1024);
		while (privateDHKey.compareTo(diffieP) == 1 && privateDHKey.compareTo(new BigInteger("2")) < 1)
		{
			privateDHKey = Utility.GeneratePrime(1024);
		}
		
		clientDHKey = Utility.FastExponentation(diffieG, privateDHKey, diffieP);
		System.out.println("Client sends Diffie-Hellman client public key: " + clientDHKey);
		
		clientMessage = new ClientMessage(clientDHKey);
		return clientMessage;
	}
	
	// Receive a public key from the server as per the Diffie-Hellman Key Agreement	
	public void ReceiveDHKey(ServerMessage message)
	{
		serverDHKey = message.ReadKey();
		currentIV = message.ReadMessage();
		System.out.println("Client receives Diffie-Hellman server public key: " + serverDHKey);
		System.out.println("Client receives initiation vector: " + currentIV);
	}

	//
	public void CombineAndEncryptDHKey()
	{
		combinedDHKey = Utility.FastExponentation(serverDHKey, privateDHKey, diffieP);
		encryptedDHKey = Utility.GetSha256(combinedDHKey.toString());
		System.out.println("Client combines and encrypts Diffie-Hellman public keys: " + encryptedDHKey);		
	}
	
	//
	public ClientMessage SendEncryptedMessage() throws Exception
	{
		String cbcMessage = ("Clients are superior to a server, who should simply stop trying.");
		System.out.println("Client encrypts a message: " + cbcMessage);
		
		// Encrypt message with CBC
		cbcMessage = Utility.CBCEncryption(Utility.StringToHex(cbcMessage), encryptedDHKey, currentIV);
		System.out.println("Client sends a message to server: " + cbcMessage);
		
		// Integrity with HMAC
		String hmacMessage = Utility.HMAC(encryptedDHKey, cbcMessage);
		
		return new ClientMessage(new String[]{cbcMessage, hmacMessage});
	}
	
	//
	public String ReceiveEncryptedMessage(ServerMessage serverMessage) throws Exception
	{
		String[] message = serverMessage.ReadStringArray();
		System.out.println("Client receives a message: " + message[0]);
		
		// Encrypt message with CBC
		String decryptMessage = Utility.CBCDecryption(message[0], encryptedDHKey, currentIV);
		System.out.println("Client decrypts a message: " + decryptMessage);
		
		// Integrity with HMAC
		// decryptMessage = Utility.HMAC(encryptedDHKey, decryptMessage);
		
		return new ClientMessage(message).ReadMessage();
	}
	
}

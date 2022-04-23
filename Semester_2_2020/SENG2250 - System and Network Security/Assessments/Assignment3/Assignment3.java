public class Assignment3 
{
	public static void main (String[] args) throws Exception
	{	    			
		Client client = new Client();
		Server server = new Server();		
		
		
		
		// initial connection - send "Hello" from client to server, share public key 
		System.out.println("== Initial greeting ==");
		ClientMessage clientMessage = client.ConnectToServer();
		server.ReceiveConnection(clientMessage);
		System.out.println();
		
		// server shares public key with client
		client.ReceivePublicKey(server.SendPublicKey());
		System.out.println();

		System.out.println("== Start handshake ==");
		// server and client share IDs
		client.ReceiveHello(server.SendHello());
		System.out.println();
		server.ReceiveHello(client.SendHello());
		System.out.println();
		

		client.ReceiveRSASignature(server.SendRSASignature());
		System.out.println();

		// Client and server generate a public key each and exchange
		client.ReceiveDHKey(server.SendDHKey());
		System.out.println();
		server.ReceiveDHKey(client.SendDHKey());

		System.out.println();
		client.CombineAndEncryptDHKey();
		server.CombineAndEncryptDHKey();
		System.out.println();

		System.out.println("== End handshake ==\n");

		System.out.println("== Send first message ==\n");
		server.ReceiveEncryptedMessage(client.SendEncryptedMessage());
		System.out.println("\n== Send second message ==\n");
		client.ReceiveEncryptedMessage(server.SendEncryptedMessage());
		System.out.println();
	}
}

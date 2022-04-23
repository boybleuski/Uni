import java.math.BigInteger;

public class ServerMessage 
{
	private String messageBody;
	private String[] stringArray;
	private BigInteger key;
	private BigInteger[] keyPair;
	private BigInteger[] keyTrio;
	
	public ServerMessage(String serverMessage)
	{
		messageBody = serverMessage;
	}
	
	public ServerMessage(String[] serverMessage)
	{
		stringArray = serverMessage;
	}
	
	public ServerMessage(BigInteger value)
	{
		key = value;
	}
	
	public ServerMessage(String serverMessage, BigInteger value)
	{
		messageBody = serverMessage;
		key = value;
	}
	
	public String ReadMessage()
	{
		return messageBody;
	}
	
	public BigInteger ReadKey()
	{
		return key;
	}
	
	public String[] ReadStringArray()
	{
		return stringArray;
	}
	
	public BigInteger[] ReadKeyPair()
	{
		return keyPair;
	}
	
	public BigInteger[] ReadKeyTrio()
	{
		return keyTrio;
	}
	
	public void AddKeyPair(BigInteger[] pair)
	{
		keyPair = pair;
	}

	public void AddKeyTrio(BigInteger[] trio)
	{
		keyTrio = trio;
	}
}

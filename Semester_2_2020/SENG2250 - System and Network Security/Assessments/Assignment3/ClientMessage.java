import java.math.BigInteger;

public class ClientMessage 
{
	private String messageBody;
	private String[] stringArray;
	private BigInteger key;
	private BigInteger[] keyPair;
	private BigInteger[] keyTrio;
	
	public ClientMessage(String clientMessage)
	{
		messageBody = clientMessage;
	}
	
	public ClientMessage(String[] clientMessage)
	{
		stringArray = clientMessage;
	}
	
	public ClientMessage(BigInteger value)
	{
		key = value;
	}
	
	public ClientMessage(String clientMessage, BigInteger value)
	{
		messageBody = clientMessage;
		key = value;
	}
	
	public String ReadMessage()
	{
		return messageBody;
	}
	
	public String[] ReadStringArray()
	{
		return stringArray;
	}
	
	public BigInteger ReadKey()
	{
		return key;
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

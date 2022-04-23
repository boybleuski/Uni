import java.util.concurrent.Semaphore;

public class FarmerSemaphore extends Semaphore
{
	private int neon;
	
	public FarmerSemaphore(int _permits)
	{
		super(_permits);
	}
	
	public int GetNeon()
	{
		return neon;
	}
	
	public void IncrementNeon()
	{
		neon++;
	}
}

import java.util.concurrent.Semaphore;

public class CustomerSemaphore extends Semaphore
{
	private int time = 0;
	private int completed = 0;
	private boolean blocked;
	
	public CustomerSemaphore(int _permits)
	{
		super(_permits);
	}
	
	public void acquire()
	{
		try 
		{
			if (availablePermits() == 0)
				Block();
			else
				super.acquire();
		} 
		catch (InterruptedException e) { }
		
	}
	
	public void release()
	{
		if (IsBlocked() && availablePermits() == 3)
			Unblock();
		super.release();
	}
	
	public int GetTime()
	{
		return time;
	}
	
	public int GetCompleted()
	{
		return completed;
	}
	
	public boolean IsBlocked()
	{
		return blocked;
	}
	
	public void IncrementTime()
	{
		time++;
	}
	
	public void IncrementCompleted()
	{
		completed++;
	}
	
	public void Block()
	{
		blocked = true;
	}
	
	public void Unblock()
	{
		blocked = false;
	}
}

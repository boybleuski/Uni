public class Farmer extends Thread
{
	private String name, direction;
	private int process;
	private static FarmerSemaphore semaphore;
	
	public Farmer(String _name, FarmerSemaphore _semaphore, String _direction)
	{
		name = _name;
		semaphore = _semaphore;
		direction = _direction;
	}
	
	public void run()
	{
		try 
		{
			do
			{
				ChangeDirection();
				System.out.println(name + ": Waiting for the bridge.  Going towards " + direction);
				semaphore.acquire();
				this.sleep(50);
				System.out.println(name + ": Crossing bridge Step 5");
				this.sleep(50);
				System.out.println(name + ": Crossing bridge Step 10");
				this.sleep(50);
				System.out.println(name + ": Across the bridge.");
				semaphore.release();
				semaphore.IncrementNeon();
				System.out.println("NEON: " + semaphore.GetNeon());
			}
			while(true);
		}
		catch (InterruptedException e) { }
		finally
		{
			System.out.println(name + " exiting.");
		}
	}
	
	public String GetName()
	{
		return name;
	}
	
	public String GetDirection()
	{
		return direction;
	}
	
	public void SetName(String _name)
	{
		name = _name;
	}
	
	public void ChangeDirection()
	{
		if (direction == "north")
			direction = "south";
		else
			direction = "north";
	}	
}

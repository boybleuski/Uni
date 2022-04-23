public class Customer extends Thread
{
    private int arrive_time, eating_time, seat_time, id;
    private boolean finished, running;
    private static CustomerSemaphore semaphore;

    public Customer(int _arrive_time, int _eating_time, int _id, CustomerSemaphore _semaphore)
    {
        arrive_time = _arrive_time;
        eating_time = _eating_time;
        id = _id;
        semaphore = _semaphore;
        finished = false;
        running = false;
    }
    
    public void run()
    {
    	do
    	{
	    	if ((semaphore.GetTime() >= arrive_time) && !semaphore.IsBlocked() && !IsRunning())
	    	{
	    		semaphore.acquire();
	    		seat_time = semaphore.GetTime();
	    		running = true;
	    	}
	    	else if (semaphore.GetTime() >= (seat_time + eating_time))
	    	{
	    		semaphore.release();
	    		running = false;
	    		finished = true;
	    		semaphore.IncrementCompleted();
	    	}
    	}
    	while(!IsFinished());
    }

    public int GetArriveTime()
    {
        return arrive_time;
    }

    public int GetEatingTime()
    {
        return eating_time;
    }

    public int GetSeatTime()
    {
        return seat_time;
    }
    
    public int GetLeavingTime()
    {
    	return (seat_time + eating_time);
    }

    public int GetId()
    {
        return id;
    }

    public void SetSeatTime(int _seat_time)
    {
        seat_time = _seat_time;
    }
    
    public boolean IsFinished()
    {
    	return finished;
    }
    
    public void Finish()
    {
    	finished = true;
    }
    
    public boolean IsRunning()
    {
    	return running;
    }
}
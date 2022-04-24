import java.util.Random;
import java.util.List;
import java.util.PriorityQueue;

public class ProductStage
{
    private Boolean starved = true;
    private Boolean blocked = false;
    private int prevQueue, nextQueue;
    private double avgTime, rangeTime, processTime;
    private double timeStarved, timeBlocked, timeElapsed = 0;
    private String stageName;
    private Random rnd;
    private Item processing;

    public ProductStage(double _avgTime, double _rangeTime, String _stageName, int _prevQueue, int _nextQueue)
    {
        avgTime = _avgTime;
        rangeTime = _rangeTime;
        stageName = _stageName;
        prevQueue = _prevQueue;
        nextQueue = _nextQueue;
        rnd = new Random();
    }

    public double GetAverageTime()
    {
        return avgTime;
    }

    public String GetStageName()
    {
        return stageName;
    }

    public double GetProcessTime()
    {
        return processTime;
    }

    public int GetPrevQueue()
    {
        return prevQueue;
    }

    public int GetNextQueue()
    {
        return nextQueue;
    }

    public void Starve()
    {
        starved = true;
    }

    public void Unstarve()
    {
        starved = false;
    }

    public Boolean Starved()
    {
        return starved;
    }

    public void Block()
    {
        blocked = true;
    }

    public void Unblock()
    {
        blocked = false;
    }

    public Boolean Processing()
    {
        if (processing == null)
            return false;
        else
            return true;
    }

    public Boolean Blocked()
    {
        return blocked;
    }

    public double GetBlockTime()
    {
        return timeBlocked;
    }

    public double GetStarveTime()
    {
        return timeStarved;
    }

    public double GetElapsedTime()
    {
        return timeElapsed;
    }

    public void TimeBlocked(double _timeBlocked)
    {
        timeBlocked += _timeBlocked;
    }

    public void TimeStarved(double _timeStarved)
    {
        timeStarved += _timeStarved;
    }

    public void TimeElapsed(double _timeElapsed)
    {
        timeElapsed = _timeElapsed;
    }

    public Item FinishProcessing()
    {
        Item processed = processing;
        processing = null;
        return processed;
    }

    public void Process(Item item, double currentTime)
    {
        if (blocked)
            TimeBlocked(1000);
        if (starved)
            TimeStarved(1000);

        if (item != null)
        {
            processTime = avgTime+rangeTime*(rnd.nextDouble() - 0.5);
            processing = item;
            processing.FillStages(stageName);
            processing.AddToTime(processTime);
            TimeElapsed(currentTime + processTime);
        }
    }
}

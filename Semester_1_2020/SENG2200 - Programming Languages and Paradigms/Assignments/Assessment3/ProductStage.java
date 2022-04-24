import java.util.Random;

public class ProductStage
{
    private int prevQueue, nextQueue;
    private double avgTime, rangeTime, processTime;
    private double timeStarved, timeBlocked, totalTime, starvedSectionStart, blockedSectionStart = 0;
    private String stageName;
    private Boolean starved = true;
    private Boolean blocked = false;
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

    // the name of the stage
    public String GetStageName()
    {
        return stageName;
    }

    // the index of the previous queue, where items to be processed in this stage are retrieved from.
    public int GetPrevQueue()
    {
        return prevQueue;
    }

    // the index of the next queue, where items processed in this stage are inserted into.
    public int GetNextQueue()
    {
        return nextQueue;
    }

    // the percentage of time that the stage is processing (not blocked or starved)
    public double GetWorkPercentage(double time)
    {
        return (100 - ((GetStarveTime() + GetBlockTime()) / time) * 100);
    }

    // get the total amount of time that the stage has spent blocked
    public double GetBlockTime()
    {
        return timeBlocked;
    }

    // get the total amount of time that the stage has spent starved
    public double GetStarveTime()
    {
        return timeStarved;
    }

    // get the time that the stage will finish processing (if currently processing)
    public double GetTotalTime()
    {
        return totalTime;
    }

    // if the stage is not currently starved, starve it.
    public void Starve(double _currentTime)
    {
        if (!Starved())
        {
            starvedSectionStart = _currentTime;
            starved = true;
        }
    }

    // if the stage is currently starved, unstarve it.
    public void Unstarve(double _currentTime)
    {
        if (Starved())
        {
            TimeStarved(_currentTime - starvedSectionStart);
            starved = false;
        }
    }

    // return true if the stage is starved.
    public Boolean Starved()
    {
        return starved;
    }

    // if the stage is not currently blocked, block it.
    public void Block(double _currentTime)
    {
        if (!Blocked())
        {
            blockedSectionStart = _currentTime;
            blocked = true;
        }
    }

    // if the stage is currently blocked, unblock it.
    public void Unblock(double _currentTime)
    {
        if (Blocked())
        {
            TimeBlocked(_currentTime - blockedSectionStart);
            blocked = false;
        }
    }

    // return true if the stage is blocked, otherwise false.
    public Boolean Blocked()
    {
        return blocked;
    }

    // return true if the stage is currently processing an item, otherwise false.
    public Boolean Processing()
    {
        if (processing == null)
            return false;
        else
            return true;
    }

    // update the amount of time that the stage has spent blocked.
    public void TimeBlocked(double _timeBlocked)
    {
        timeBlocked += _timeBlocked;
    }

    // update the amount of time that the stage has spent starved.
    public void TimeStarved(double _timeStarved)
    {
        timeStarved += _timeStarved;
    }

    // update the time that the stage will finish processing the current item.
    public void TotalTime(double _totalTime)
    {
        totalTime = _totalTime;
    }

    // return the currently processed item and null the reference to it
    public Item FinishProcessing()
    {
        Item processedItem = processing;
        processing = null;
        return processedItem;
    }

    // if item is not null, start to process it
    public void Process(Item item, double currentTime)
    {
        if (item != null)
        {
            processTime = avgTime+rangeTime*(rnd.nextDouble() - 0.5);   // set a random time within the parameters that the item will spend processing
            processing = item;  // set a reference to the item
            processing.FillStages(stageName);   // add the current stage name to the item's stage path
            processing.AddToProcessTime(processTime);   // add the time the item will spend processing to the list of the item's process times
            TotalTime(currentTime + processTime);   // designate the time that the stage will stop processing the item
        }
    }
}

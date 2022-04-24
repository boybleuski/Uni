import java.util.ArrayList;
import java.lang.Object;

public class Item extends Object implements Comparable<Item>
{
    private ArrayList<Double> timeForProd, timeInQueue;
    private double currentQueueStart;
    private String stages, itemID;

    public Item(String _itemID)
    {
        timeForProd = new ArrayList<>(7);   // the list of production times per stage
        timeInQueue = new ArrayList<>(6);   // the list of waiting times per queue
        stages = "";        // the path of stages the item took to be fully processed
        itemID = _itemID;   // unique ID assigned to an item
    }

    /*
        The Item class needs the compareTo method since it implements Comparable<Item>,
        which was necessary to use the PriorityQueue extension.  Not actually used.
     */
    public int compareTo(Item _item)
    {
        return 0;
    }

    // return the path of stages
    public String GetStages()
    {
        return stages;
    }

    // return the ArrayList of times that the Item spent in each queue
    public ArrayList<Double> GetQueueTime()
    {
        return timeInQueue;
    }

    // return the item's unique ID
    public String GetID()
    {
        return itemID;
    }

    // add the amount of time that the Item spent in the queue that it is currently leaving
    public void AddToQueueTime(double _time)
    {
        timeInQueue.add(_time - currentQueueStart); // queue time is the current system time less the system time when the item entered the queue
    }

    // add the amount of time that the Item spent being processed in the current stage
    public void AddToProcessTime(double _time)
    {
        timeForProd.add(_time);
    }

    // set the time that the item enters the queue
    public void StartCurrentQueueTime(double _time)
    {
        currentQueueStart = _time;
    }

    // add to/complete the stage path string with a substring of the current stage
    public void FillStages(String stageName)
    {
        if (stageName.contains("6"))
            stages += (stageName);  // add stage name substring to stages - no arrow to the next stage if its the last stage
        else
            stages += (stageName + " -> "); // add stage name substring to stages - arrow to the next stage
    }
}

import java.util.PriorityQueue;

public class StorageQueue<E> extends PriorityQueue<E>
{
    private double totalItems, totalTime, numberOfRounds = 0;
    private String queueName;

    public StorageQueue(String name)
    {
        queueName = name;
    }

    public double GetTotalItems()
    {
        return totalItems;
    }

    public double GetRounds()
    {
        return numberOfRounds;
    }

    public double GetAverage()
    {
        return totalTime/totalItems;
    }

    public String GetName()
    {
        return queueName;
    }

    public void AddItems(int items)
    {
        totalItems += items;
    }

    public void AddTime(double time)
    {
        totalTime += time;
    }

    public void AddRound(int items)
    {
        numberOfRounds += items;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

public class Item extends Object implements Comparable<Item>
{
    private List<Double> timeForProd;
    private double timeInQueue;
    private double totalProdTime;
    private String stages;

    public Item()
    {
        totalProdTime = 0;
        timeForProd = new ArrayList<Double>();
        stages = "0";
        timeInQueue = 0;
    }

    public int compareTo(Item _item)
    {
        return 0;
    }

    public String GetStages()
    {
        return stages;
    }

    public double GetQueueTime()
    {
        return timeInQueue;
    }

    public void SetQueueTime(double _timeInQueue)
    {
        timeInQueue = _timeInQueue;
    }

    public double GetTime(int i)
    {
        return timeForProd.get(i);
    }

    public void AddToTime(double time)
    {
        timeForProd.add(time);
    }

    public void FillStages(String stageName)
    {
        stages += (" -> " + stageName);
    }

    public void GetTotalTime()
    {
        totalProdTime = 0;
        for (int i = 0; i < timeForProd.size(); i++)
        {
            totalProdTime += timeForProd.get(i);
        }
    }
}

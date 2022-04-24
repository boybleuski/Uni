import java.util.PriorityQueue;

public class StorageQueue<E> extends PriorityQueue<E>
{
    private String name;    // name of the queue

    public StorageQueue(String _name)
    {
        name = _name;
    }

    // get the name assigned to the queue
    public String GetName()
    {
        return name;
    }
}

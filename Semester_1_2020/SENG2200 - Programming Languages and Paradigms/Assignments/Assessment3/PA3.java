import java.text.DecimalFormat;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;

public class PA3
{
    private static final double TIME_LIMIT = 10000000;	// processing stops at 10,000,000 time units

    public static int median(int x, int y, int z)
    {
        assertTrue();
    }

    public static void main(String[] args)
    {
        double m = Double.parseDouble(args[0]);			// average item processing time per stage
        double n = Double.parseDouble(args[1]);			// range of item processing time per stage
        int qMax = Integer.parseInt(args[2]);			// max size of inter-storage queues

        ProductStage[] stageList = new ProductStage[10];		// array of production stages
        ArrayList storage = new ArrayList<Item>(); 				// array list of completed items
        StorageQueue<Item>[] queueList = new StorageQueue[6];	// array of inter-storage queues
        for (int i = 0; i < queueList.length; i++)
            queueList[i] = new StorageQueue<Item>("Q" + i + (i+1));	// fill array with queues - names are Q<previous stage><next stage>

        int tempIndex = 0;			// temp variable used to link queues to the right stages - separate from int i
        for (int i = 0; i < stageList.length; i++)
        {
            // define stage 0a and 0b
            if (tempIndex == 0)
            {
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex)+"a", -2, tempIndex);	// index -2 is an int reference to the "factory" - a functionally infinite queue where items are drawn from
                stageList[i].Unstarve(0);
                i++;
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex)+"b", -2, tempIndex);	// all split stages have double the average/range production time in each stage.
                stageList[i].Unstarve(0);
            }
            // define stage 3a, 3b, 5a and 5b
            else if (tempIndex == 3 || tempIndex == 5)
            {
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex)+"a", tempIndex-1, tempIndex);
                i++;
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex)+"b", tempIndex-1, tempIndex);
            }
            // define stage 6
            else if (tempIndex == 6)
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex), tempIndex-1, -1);    // index -1 is an int reference to "storage" - a functionally infinite queue of completed items.
            // define the remaining stages
            else
                stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex), tempIndex-1, tempIndex);

            tempIndex++;
        }

        double currentTime = 0;     // track total processing time as a link to all other queues and stages
        int idNumberA = 1;          // build unique IDs for items starting in stage 0a
        int idNumberB = 1;          // build unique IDs for items starting in stage 0b

        do
        {
            for (int j = 0; j < stageList.length; j++)
            {
                int prevQueue = stageList[j].GetPrevQueue();    // get index of the queue that the stage take from
                int nextQueue = stageList[j].GetNextQueue();    // get index of the queue that the stage put into
                // check if the current stage is not processing an item
                if (!stageList[j].Processing())
                {
                    // condition for initial stage 0a/0b
                    if (j == 0 || j == 1)
                    {
						if (queueList[nextQueue].size() >= qMax)    // initial stage can be blocked but not starved
							stageList[j].Block(currentTime);
						else
							stageList[j].Unblock(currentTime);

						if (!stageList[j].Blocked())
                        {
                            Item newItem;
                            if (j == 0) // id number ends with A for stage 0a
                            {
                                newItem = new Item(idNumberA + "A");
                                idNumberA++;
                            }
                            else        // id number ends with B for stage 0b
                            {
                                newItem = new Item(idNumberB + "B");
                                idNumberB++;
                            }
                            stageList[j].Process(newItem, currentTime); // start processing a new item
                        }
                    }
                    // condition for final stage 6
                    else if (j == stageList.length-1)
                    {
						if (queueList[prevQueue].size() == 0)
							stageList[j].Starve(currentTime);
						else
							stageList[j].Unstarve(currentTime);

						if (!stageList[j].Starved())    // final stage can be starved but not blocked
						{
                            Item moveItem = queueList[prevQueue].poll();    // remove item least recently inserted into queue
                            moveItem.AddToQueueTime(currentTime);
                            stageList[j].Process(moveItem, currentTime);    // finish processing an item
                        }
                    }
                    // condition for remaining stages
                    else
                    {
						if (queueList[prevQueue].size() == 0)
							stageList[j].Starve(currentTime);
						else
							stageList[j].Unstarve(currentTime);

						if (queueList[nextQueue].size() >= qMax)
							stageList[j].Block(currentTime);
						else
							stageList[j].Unblock(currentTime);


						if (!stageList[j].Blocked() && !stageList[j].Starved()) // ensure stage is not starved or blocked
						{
                            Item moveItem = queueList[prevQueue].poll();    // remove item least recently inserted into queue
                            moveItem.AddToQueueTime(currentTime);
                            stageList[j].Process(moveItem, currentTime);    // continue processing an item
                        }
                    }
                }
                else
                {
                    if (stageList[j].GetTotalTime() < currentTime)    // check if stage has finished processing an item
                    {
                        Item moveItem = stageList[j].FinishProcessing();    // present item post-processing
                        if (nextQueue != -1)    // check the next queue is not the final storage
                        {
                            if (moveItem != null)   // extra insurance against NPEs
                            {
                                moveItem.StartCurrentQueueTime(currentTime);    // set time that item is placed into the next queue
                                queueList[nextQueue].add(moveItem);   // place item into next queue
                                stageList[j+1].Unstarve(currentTime);           // unstarve the next queue if it is not already
                            }
                        }
                        else
                        {
                            storage.add(moveItem);  // if the item is completing its last processing, add it to the infinite storage.
                        }
                    }
                }
            }
            currentTime++;  // increment timer
        }
        while(currentTime < TIME_LIMIT);

        // if any stages are blocked or starved, close them and add the times to the total
        for (int i=0; i<stageList.length; i++)
        {
            if (stageList[i].Blocked())
                stageList[i].Unblock(TIME_LIMIT);

            if (stageList[i].Starved())
                stageList[i].Unstarve(TIME_LIMIT);
        }

        DecimalFormat dec = new DecimalFormat("#0.00"); // all doubles are to 2 decimal places

        // list the stages, the percentage of time they spent processing, and the amount of time they spent starved and blocked.
        System.out.printf("Production Stations:\n--------------------------------------------\n");
        System.out.printf("%-5s %12s %12s %12s %n", "Stage", "Work[%]", "Starve[t]", "Block[t]");
        for(int i = 0; i < stageList.length; i++)
            System.out.printf("%-5s %12s %12s %12s %n", stageList[i].GetStageName(), dec.format(stageList[i].GetWorkPercentage(TIME_LIMIT)), dec.format(stageList[i].GetStarveTime()), dec.format(stageList[i].GetBlockTime()));

        // list the queues, the average time an item spent in the queue, and the average number of items in a queue at any given time
        System.out.printf("\nStorage Queues:\n--------------------------------------------\n");
        System.out.printf("%-5s %12s %12s %n", "Store", "AvgTime[t]", "AvgItems");
        for(int i = 0; i < queueList.length; i++)
        {
            // since starve times are linked to stages rather than queues, iterate though the list of stages and find previous queues
            double starveTime = StarveTimeByStage(stageList, i);
            System.out.printf("%-5s %12s %12s %n", queueList[i].GetName(), dec.format(AverageQueueTime(storage, i)), dec.format(AverageQueueItems(storage, i, m, starveTime)));
        }

        // list the number of items that have gone through each path.
        System.out.printf("\nProduction Paths:\n--------------------------------------------\n");
        System.out.printf("%-15s %12s %n", "S3a -> S5a", CountItemsForPath(storage, "3a -> 4 -> 5a"));
        System.out.printf("%-15s %12s %n", "S3a -> S5b", CountItemsForPath(storage, "3a -> 4 -> 5b"));
        System.out.printf("%-15s %12s %n", "S3b -> S5a", CountItemsForPath(storage, "3b -> 4 -> 5a"));
        System.out.printf("%-15s %12s %n", "S3b -> S5b", CountItemsForPath(storage, "3b -> 4 -> 5b"));

        // list the number of items that started in origin.
        System.out.printf("\nProduction Items:\n--------------------------------------------\n");
        System.out.printf("%-5s %11s %n", "S0a: ", CountItemsByID(storage, "A"));
        System.out.printf("%-5s %11s %n", "S0b: ", CountItemsByID(storage, "B"));
    }

    public static double StarveTimeByStage(ProductStage[] _stageList, int _index)
    {
        double starveTime = 0;
        for (int j = 0; j < _stageList.length; j++)
        {
            if (_stageList[j].GetPrevQueue() == _index)
            {
                if (starveTime > 0)
                {
                        /*
                            starveTime > 0 only when the queue supplies to a split stage.
                            This means that there will be 2 starveTimes, which are added and averaged.
                        */
                    starveTime += _stageList[j].GetStarveTime();
                    starveTime /= 2;
                }
                else
                    starveTime = _stageList[j].GetStarveTime();
            }
        }
        return starveTime;
    }

    // search for items whose stages include the given path value
    public static int CountItemsForPath(ArrayList<Item> _storage, String _pathValue)        {
        int count = 0;
        for (int i = 0; i < _storage.size(); i++)
        {
            Item head = _storage.get(i);
            if (head.GetStages().contains(_pathValue))  // check if the item contains the path fragment, e.g. items with substring "3a" will have been processed by stage 3a.
                count++;
        }
        return count;
    }

    // search for items whose id ends with the ID value - in this case, A or B
    public static int CountItemsByID(ArrayList<Item> _storage, String _idValue)
    {
        int count = 0;
        for (int i = 0; i < _storage.size(); i++)
        {
            Item head = _storage.get(i);
            if (head.GetID().endsWith(_idValue))    // check if the item ends with the ID fragment
                count++;
        }
        return count;
    }

    // the average amount of time an item spends in the given queue
    public static double AverageQueueTime(ArrayList<Item> _storage, int _index)
    {
        double averageTime = 0;
        for (int i = 0; i < _storage.size(); i++)   // iterate through the storage
        {
            Item head = _storage.get(i);
            averageTime += head.GetQueueTime().get(_index); // with each item in storage, add up all the times associated with int index.
        }

        averageTime = averageTime / _storage.size();    // divide by the number of items in storage
        return averageTime;
    }

    // the average number of items in a queue at any given time
    public static double AverageQueueItems(ArrayList<Item> _storage, int _index, double _m, double _starveTime)
    {
        /*
           To calculate the average items in the queue at any given time:
            - calculate the amount of time the average item spends in the queue,
            - multiply by the percentage of time that the queue spends ACTIVE (i.e. not starved), and
            - divide by the average amount of time an item spends processing per stage (m);
         */
        return (AverageQueueTime(_storage, _index) * ((TIME_LIMIT - _starveTime) / TIME_LIMIT)) / _m;
    }
}
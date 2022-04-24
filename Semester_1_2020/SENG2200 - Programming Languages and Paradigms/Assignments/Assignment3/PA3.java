import java.text.DecimalFormat;
import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;

public class PA3
{
	public static final double TIME_LIMIT = 10000;	// processing stops at 10,000,000 time units
	public static Random r = new Random();				// RNG to determine inter-stage production times

	public static void main(String[] args)
	{
		double m = Double.parseDouble(args[0]);			// average item processing time per stage
		double n = Double.parseDouble(args[1]);			// range of item processing time per stage
		int qMax = Integer.parseInt(args[2]);			// max size of inter-storage queues
		int[] productionPaths = new int[4];

		ProductStage[] stageList = new ProductStage[10];		// array of production stages
		ArrayList storage = new ArrayList<Item>(); 				// array list of completed items
		StorageQueue<Item>[] queueList = new StorageQueue[6];	// array of inter-storage queues
		for (int i = 0; i < queueList.length; i++)
			queueList[i] = new StorageQueue<Item>("Q" + i + (i+1));	// fill array with queues - names are Q<previous stage><next stage>

		int tempIndex = 0;			// temp variable used to link queues to the right stages - separate from int i
		for (int i = 0; i < stageList.length; i++)
		{
			if (tempIndex == 0)		// define stage 0a and 0b
			{
				stageList[i] = new ProductStage(m*2, n*2, Integer.toString(tempIndex)+"a", -2, tempIndex);	// index -2 is an int reference to the "factory" - a functionally infinite queue where items are drawn from
				i++;
				stageList[i] = new ProductStage(m*2, n*2, Integer.toString(tempIndex)+"b", -2, tempIndex);	// all split stages have double the average/range production time in each stage.
			}
			else if (tempIndex == 3 || tempIndex == 5)	// define stage 3a, 3b, 5a and 5b
			{
				stageList[i] = new ProductStage(m*2, n*2, Integer.toString(tempIndex)+"a", tempIndex-1, tempIndex);
				i++;
				stageList[i] = new ProductStage(m*2, n*2, Integer.toString(tempIndex)+"b", tempIndex-1, tempIndex);
			}
			else if (tempIndex == 6)	// define stage 6
				stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex), tempIndex-1, -1);		// index -1 is an int reference to "storage" - a functionally infinite queue of completed items.
			else						// define the remaining stages
				stageList[i] = new ProductStage(m, n, Integer.toString(tempIndex), tempIndex-1, tempIndex);

			tempIndex++;
		}
		/*for (int i = 0; i < stageList.length; i++)
		{
			if (i == 0 || i == 1)
				System.out.printf("%-15s %12s %12s %12s %n", "Name:", stageList[i].GetStageName(), "factory", queueList[stageList[i].GetNextQueue()].GetName());
			else if (i == stageList.length-1)
				System.out.printf("%-15s %12s %12s %12s %n", "Name:", stageList[i].GetStageName(), queueList[stageList[i].GetPrevQueue()].GetName(), "storage");
			else
				System.out.printf("%-15s %12s %12s %12s %n", "Name:", stageList[i].GetStageName(), queueList[stageList[i].GetPrevQueue()].GetName(), queueList[stageList[i].GetNextQueue()].GetName());
		}*/
		/*do
		{
			double currentRound = m+n*(r.nextDouble()-0.5);
			for (int j = 0; j < stageList.length-1; j++)
			{
				int prevQueue = 0;
				int nextQueue = 0;
				if (stageList[j].GetElapsedTime() < currentTime)
				{
					if (j != 0)
						prevQueue = stageList[j].GetPrevQueue();

					if (j != stageList.length-1)
						nextQueue = stageList[j].GetNextQueue();

					if (j == 0)
					{
						Item newItem = new Item(r.nextDouble());

						if (queueList[nextQueue].size() == qMax)
							stageList[j].Block();
						else
							stageList[j].Unblock();

						if (stageList[j].Processing())
						{
							Item moveItem = stageList[j].FinishProcessing(currentRound, currentTime);
							if (moveItem != null)
							{
								moveItem.SetQueueTime(0);
								queueList[nextQueue].add(moveItem);
							}
						}
						if (!stageList[j].Processing())
						{
							if (stageList[j].Blocked())
								stageList[j].Process(null, currentRound);
							else
								stageList[j].Process(newItem, currentRound);
						}
					}
					else if (j == stageList.length-1)
					{
						if (queueList[prevQueue].size() == 0)
							stageList[j].Starve();
						else
							stageList[j].Unstarve();

						if (stageList[j].Processing())
						{
							Item moveItem = stageList[j].FinishProcessing(currentRound, currentTime);

							if (moveItem != null)
							{
								for (int i = 1; i < queueList.length-1; i++)
								{
									queueList[i].AddTime(moveItem.GetTime(i-1));
								}

								if (moveItem.GetStages().contains("3a"))
								{
									if (moveItem.GetStages().contains("5a"))
										productionPaths[0]++;
									else
										productionPaths[1]++;
								}
								else
								{
									if (moveItem.GetStages().contains("5a"))
										productionPaths[2]++;
									else
										productionPaths[3]++;
								}
							}
						}
						if (!stageList[j].Processing())
						{
							if (stageList[j].Starved())
								stageList[j].Process(null, currentRound);
							else
							{
								Item moveItem = queueList[prevQueue].poll();
								stageList[j].Process(moveItem, currentRound);
							}
						}
					}
					else
					{
						if (stageList[j].GetPrevQueue() != -2 && queueList[prevQueue].size() == 0) // Ensure that the preceding queue is not empty - avoid NullReference
							stageList[j].Starve();
						else
							stageList[j].Unstarve();

						if (stageList[j].GetNextQueue() != -1 && queueList[nextQueue].size() == qMax)
							stageList[j].Block();
						else
							stageList[j].Unblock();

						if (stageList[j].Processing())
						{
							Item moveItem = stageList[j].FinishProcessing(currentRound, currentTime);
							if (moveItem != null)
							{
								moveItem.SetQueueTime(0);
								queueList[nextQueue].add(moveItem);
							}
						}
						if (!stageList[j].Processing())
						{
							if (stageList[j].Blocked() || stageList[j].Starved())
								stageList[j].Process(null, currentRound);
							else
							{
								Item moveItem = queueList[prevQueue].poll();
								stageList[j].Process(moveItem, currentRound);
							}
						}
					}
				}
			}
			currentTime += currentRound;
			for (int k = 0; k < queueList.length-1; k++)
			{
				Iterator<Item> iter = queueList[k].iterator();
				while (iter.hasNext())
				{
					iter.next().SetQueueTime(currentRound);
				}
				queueList[k].AddRound(1);
				queueList[k].AddItems(queueList[k].size());
			}
		}
		while(currentTime < TIME_LIMIT);*/
		double currentTime = 0;							// track total processing time

		do
		{
			for (int j = 0; j < stageList.length-1; j++)
			{
				int prevQueue = 0;
				int nextQueue = 0;
				if (!stageList[j].Processing())
				{
					prevQueue = stageList[j].GetPrevQueue();
					nextQueue = stageList[j].GetNextQueue();
					if (j == 0 || j == 1)
					{
						Item newItem = new Item();
						/*if (queueList[nextQueue].size() == qMax)
							stageList[j].Block();
						else
							stageList[j].Unblock();

						if (!stageList[j].Blocked())*/
							stageList[j].Process(newItem, currentTime);
					}
					else if (j == stageList.length-1)
					{
						/*if (queueList[prevQueue].size() == 0)
							stageList[j].Starve();
						else
							stageList[j].Unstarve();

						if (stageList[j].Starved())
							stageList[j].Process(null, currentTime);
						else
						{*/
							Item moveItem = queueList[prevQueue].poll();
							stageList[j].Process(moveItem, currentTime);
						//}
					}
					else
					{
						/*if (queueList[prevQueue].size() == 0) // Ensure that the preceding queue is not empty - avoid NullReference
							stageList[j].Starve();
						else
							stageList[j].Unstarve();

						if (queueList[nextQueue].size() == qMax)
							stageList[j].Block();
						else
							stageList[j].Unblock();


						if (stageList[j].Blocked() || stageList[j].Starved())
							stageList[j].Process(null, currentTime);
						else
						{*/
							Item moveItem = queueList[prevQueue].poll();
							stageList[j].Process(moveItem, currentTime);
						//}
					}
				}
				else
				{
					if (stageList[j].GetElapsedTime() < currentTime)
					{
						Item moveItem = stageList[j].FinishProcessing();
						if (stageList[j].GetNextQueue() != -1)
						{
							if (moveItem != null)
							{
								moveItem.SetQueueTime(0);
								queueList[nextQueue].add(moveItem);
							}
						}
						else
						{
							storage.add(moveItem);
						}
					}
				}
			}

			/*for (int k = 0; k < queueList.length-1; k++)
			{
				Iterator<Item> iter = queueList[k].iterator();
				while (iter.hasNext())
				{
					iter.next().SetQueueTime(currentRound);
				}
				queueList[k].AddRound(1);
				queueList[k].AddItems(queueList[k].size());
			}*/

			currentTime++;
		}
		while(currentTime < TIME_LIMIT);

		DecimalFormat dec = new DecimalFormat("#0.00");

		System.out.printf("Production Stations:\n--------------------------------------------\n");
		System.out.printf("%-5s %12s %12s %12s %n", "Stage", "Work[%]", "Starve[t]", "Block[t]");
		for(int i = 0; i < stageList.length; i++)
			System.out.printf("%-5s %12s %12s %12s %n", stageList[i].GetStageName(), dec.format((100 - ((stageList[i].GetStarveTime() + stageList[i].GetBlockTime()) / TIME_LIMIT) * 100)), dec.format(stageList[i].GetStarveTime()), dec.format(stageList[i].GetBlockTime()));

		System.out.printf("\nStorage Queues:\n--------------------------------------------\n");
		System.out.printf("%-5s %12s %12s %n", "Store", "AvgTime[t]", "AvgItems");
		for(int i = 0; i < queueList.length; i++)
		{
			String queueName = "Q" + i;
			System.out.printf("%-5s %12s %12s %n", queueList[i].GetName(), dec.format(queueList[i].GetAverage()), dec.format(queueList[i].GetTotalItems()/queueList[i].GetRounds()));
		}

		System.out.printf("\nProduction Paths:\n--------------------------------------------\n");
		System.out.printf("%-15s %12s %n", "S3a -> S5a", productionPaths[0]);
		System.out.printf("%-15s %12s %n", "S3a -> S5b", productionPaths[1]);
		System.out.printf("%-15s %12s %n", "S3b -> S5a", productionPaths[2]);
		System.out.printf("%-15s %12s %n", "S3b -> S5b", productionPaths[3]);

		System.out.printf("\nProduction Items:\n--------------------------------------------\n");
		System.out.printf("%-5s %11s %n", "S0a: ", productionPaths[0]);
		System.out.printf("%-5s %11s %n", "S0b: ", productionPaths[1]);
	}
}

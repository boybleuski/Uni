import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class A2B 
{
	public static void main(String[] args) throws InterruptedException
	{
		CustomerSemaphore semaphore = new CustomerSemaphore(4);
		String file_name = "";
		if (args.length > 0)
			 file_name = args[0];
		
		ArrayList<Customer> customer_list = new ArrayList<Customer>();
		customer_list = GetCustomerList(file_name, semaphore);

		for (int i=0; i<customer_list.size(); i++)
			customer_list.get(i).start();
		
		do
		{
			semaphore.IncrementTime();
		}
		while(semaphore.GetCompleted() < customer_list.size());
		
		PrintOutput(customer_list);
	}
	
	public static ArrayList<Customer> GetCustomerList(String _file_name, CustomerSemaphore _semaphore)
	{
		ArrayList<Customer> customer_list = new ArrayList<Customer>();
		Scanner input_stream = null;
		try 
		{
			String temp_file = new File("").getAbsolutePath() + "\\" + _file_name;
			boolean end_line = false;
			input_stream = new Scanner(new File(temp_file));
			
			while (input_stream.hasNext())
			{          
				Customer new_customer;
				int id = 0, arrive_time = 0, eating_time = 0;
				String line = input_stream.next();
				if (line.contains("END") == false)
				{	
					arrive_time = Integer.parseInt(line);
					id = Integer.parseInt(input_stream.next().substring(1));
					eating_time = Integer.parseInt(input_stream.next());
					
					new_customer = new Customer(arrive_time, eating_time, id, _semaphore);
					customer_list.add(new_customer);
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (input_stream != null)
				input_stream.close();
		}
		
		return customer_list;
	}
	
	public static void PrintOutput(ArrayList<Customer> _customer_list)
	{
		System.out.println("Customer Arrives Seats Leaves");
		for (int i=0; i<_customer_list.size(); i++) 
		{
			Customer customer = _customer_list.get(i);
			System.out.println("C"+customer.GetId() + "    " + customer.GetArriveTime() + "    " + customer.GetSeatTime() + "    " + customer.GetLeavingTime());
		}
	}
}

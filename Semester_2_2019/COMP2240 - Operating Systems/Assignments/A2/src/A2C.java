import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class A2C 
{
	public static void main(String[] args)
	{
		CoffeeMonitor monitor = new CoffeeMonitor(2);
		String file_name = "";
		if (args.length > 0)
			 file_name = args[0];
		
		ArrayList<CoffeeCustomer> customer_list = new ArrayList<CoffeeCustomer>();
		customer_list = GetCustomerList(file_name, monitor);
	}
	
	public static ArrayList<CoffeeCustomer> GetCustomerList(String _file_name, CoffeeMonitor _monitor)
	{
		ArrayList<CoffeeCustomer> customer_list = new ArrayList<CoffeeCustomer>();
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
					
					new_customer = new CoffeeCustomer(arrive_time, eating_time, id, _monitor);
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
}

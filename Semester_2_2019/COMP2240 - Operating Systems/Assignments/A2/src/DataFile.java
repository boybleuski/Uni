import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataFile 
{
	private ArrayList<Customer> customer_list;  // initial list of customers
	private String file_name;				 	// name of the file containing the customer data
	
	public DataFile(String _file_name)
	{
		file_name = _file_name;
		GetCustomerData();
	}
	
	public ArrayList<Customer> GetCustomerList()
	{
		return customer_list;
	}
	
	public void GetCustomerData()
	{
	}
}

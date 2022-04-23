import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class A2A 
{
	public static void main(String[] args) 
	{
		int n_farmer=0, s_farmer=0;
		ArrayList<Farmer> north_side = new ArrayList<Farmer>(); 
		ArrayList<Farmer> south_side = new ArrayList<Farmer>();
		FarmerSemaphore semaphore = new FarmerSemaphore(1);
		
		if (args.length == 2)
		{
			if (args[0].contains("N="))
				n_farmer = GetFarmers(args[0]);
			else if (args[0].contains("S="))
				s_farmer = GetFarmers(args[0]);
			if (args[1].contains("N="))
				n_farmer = GetFarmers(args[1]);
			else if (args[1].contains("S="))
				s_farmer = GetFarmers(args[1]);
			
			for (int i=0; i<n_farmer; i++)
			{
				Farmer new_farmer = new Farmer("N_Farmer"+Integer.toString(i+1), semaphore, "north");
				north_side.add(new_farmer);
				north_side.get(i).start(); 
			}
			
			for (int i=0; i<s_farmer; i++)
			{
				Farmer new_farmer = new Farmer("S_Farmer"+Integer.toString(i+1), semaphore, "south");
				south_side.add(new_farmer);
				south_side.get(i).start(); 
			}
			
			long thread_run_time = System.currentTimeMillis();
			
			do {}
			while (semaphore.GetNeon() < 25 && System.currentTimeMillis() - thread_run_time < 5000); 
			
			ShutDownBridge(north_side);
			ShutDownBridge(south_side);
		}
		else
		{
			System.out.println("Incorrect input!");
		}
	}
	
	public static int GetFarmers(String _value)
	{
		String value;
		int farmer_number;
		if (_value.contains("N="))
			value = _value.replace("N=", "");
		else if (_value.contains("S="))
			value = _value.replace("S=", "");
		else
			value = "0";
		
		farmer_number = Integer.parseInt(value);
		return farmer_number;
	}
	
	public static void ShutDownBridge(ArrayList<Farmer> _farmers)
	{
		for (int i=0; i<_farmers.size(); i++)
		{
			_farmers.get(i).interrupt();
		}
	}
}

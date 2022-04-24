import java.util.*;

public class Salary
{

  public static void main (String[] args)
  {
		Scanner console = new Scanner(System.in);
  		double normal,extra,total;
		System.out.println("Please Enter number of normal hours: ");
		normal = console.nextDouble();
	 	System.out.println("Please Enter number of extra hours: ");
		extra = console.nextDouble();
		total = 10*normal+15*extra;
		System.out.println("Total salary is: "+total+"\n");
   }
}

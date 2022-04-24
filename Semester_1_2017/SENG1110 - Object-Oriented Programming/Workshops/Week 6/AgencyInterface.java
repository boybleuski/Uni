import java.util.*;

public class AgencyInterface 
{
    public static void main (String[] args) 
    {
       Scanner console = new Scanner(System.in);
       Couple c = new Couple();
       int      herAge,hisAge,end;
       String   herName,hisName;
       
       do {
           System.out.print("her name: "); 
           herName = console.next();
           System.out.print("her age: "); 
           herAge = console.nextInt();
           System.out.print("his name: "); 
           hisName = console.next();
           System.out.print("his age: "); 
           hisAge = console.nextInt();
           c.addData(1,herName,herAge);
           c.addData(2,hisName,hisAge);
           System.out.println("********************");
           System.out.println(c.test());           
           System.out.println("********************");
           System.out.print("Quit? (0)yes (1)no: "); 
           end = console.nextInt();
           }
       while (end!=0);
       }
}
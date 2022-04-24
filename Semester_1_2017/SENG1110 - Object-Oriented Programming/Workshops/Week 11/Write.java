import java.io.*;
import java.util.*;

public class Write
{
   public static void main (String[] args)throws IOException {
      
      Scanner console = new Scanner(System.in);
      System.out.println("\n\n File name: ");
      String fileName = console.next();
     
      PrintWriter outFile = new PrintWriter(fileName);

      outFile.println ("Random numbers"); 

      for(int i=0; i<10; i++)
      {
          outFile.println ((int)( 1 + Math.random()*10) + " "); 
      }
   
      outFile.close();
 }
}

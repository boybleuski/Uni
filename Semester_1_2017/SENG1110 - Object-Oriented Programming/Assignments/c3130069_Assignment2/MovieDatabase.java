/**
   Written by: Sam Dolbel (3130069)
   Last modified: 29/5/2017
   Class: Thursday 11-1pm
 */
import java.util.*;
import java.io.*;
public class MovieDatabase
{
    private Movie[] allMovies;
    private int logicalSize;
    
    public MovieDatabase() //constructor
    {   
       logicalSize = 4;
       allMovies = new Movie[logicalSize];
       for (int number=0; number<logicalSize; number++)
          allMovies[number] = new Movie();
    }
    
    public void defineMovie(String newName,String newDirector,int newRunTime,int newFileSize) // stores a movie within the database.
    {
       String name = newName, director = newDirector;
       int runTime = newRunTime, fileSize = newFileSize, number = 0;
       boolean stop = false;
       do
       {             
          if (allMovies[number].getName().isEmpty())
          {
             stop = true;
             allMovies[number].storeMovie(name,director,runTime,fileSize,stop);
          }
          else
          {
             number++;
             if (number==(logicalSize-1))
             {
                 boolean check = true;
                 allMovies = resizeMovieArray(check,allMovies);
             }
          }
       }
       while (stop == false);
    }
    
    public boolean cloneMovie(String newName,String newDirector,int newRunTime,int newFileSize) // searches for duplicate movies in a playlist
    {
       String name = newName, director = newDirector;
       int runTime = newRunTime, fileSize = newFileSize;
       
       for (int number=0;number<logicalSize;number++)
          if (name.equals(allMovies[number].getName()) && director.equals(allMovies[number].getDirector()) && runTime == allMovies[number].getRunTime())
             return true;
       return false;
    }
    
    public String showList(int number) // prints the list of movies stored in the database.
    {
       String newName,newDirector;
       int newFileSize,newRunTime;
       if (allMovies[number].getName().isEmpty())
          return ((number+1)+": No data");
       else
       {
          newName = allMovies[number].getName();
          newDirector = allMovies[number].getDirector();
          newFileSize = allMovies[number].getFileSize();
          newRunTime = allMovies[number].getRunTime();
          return ((number+1)+": Movie name: "+newName+"\nDirector: "+newDirector+"\nfileSize: "+newFileSize+"MB\nduration: "+newRunTime+" minutes\n");
       }
    }
    
    public boolean emptyList() // tests whether the database is empty
    {  
       boolean full = true;
       for (int number=0; number<logicalSize; number++)
          if (!allMovies[number].getName().isEmpty())
             full = false;
       return full;
    }
    
    public boolean testForPlaylist(int newSelect) // tests whether a selection represents a valid movie in the database
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1 && allMovies[number].getName()!="")
             return true;
       return false;
    }
    
    public String nameToPlaylist(int newSelect) // outputs a movie title to add to a playlist
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1)
             return allMovies[number].getName();
       return "Impossible error.";
    }
    
    public String directorToPlaylist(int newSelect)  // outputs a director name to add to a playlist
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1)
             return allMovies[number].getDirector();
       return "Error you won't see.";
    }
    
    public int runTimeToPlaylist(int newSelect)  // outputs movie length to add to a playlist
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1)
             return allMovies[number].getRunTime();
       return 0;
    }
    
    public int fileSizeToPlaylist(int newSelect)  // outputs file size to add to a playlist
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1)
             return allMovies[number].getFileSize();
       return 0;
    }
    
    public boolean movieExists(int newSelect)  // tests whether a movie exists
    {
       int select = newSelect;
       for (int number=0; number<logicalSize; number++)
          if (select == number+1 && allMovies[number].getName()!="")
             return true;
       return false;
    }
    
    public String deleteMovie(int newSelect)  // deletes a movie from the database and all playlists
    {
       int select = newSelect;
       String nameToErase = "Invalid option.";
       for (int number=0; number<logicalSize; number++)
          if (select == number+1 && allMovies[number].getName()!="")
          {
             nameToErase = allMovies[number].getName();
             allMovies[number].eraseMovie();
             if ((logicalSize/2)>(allMovies[number].getNumberOfMovies()))
             {
                boolean check = false;
                allMovies = resizeMovieArray(check,allMovies);
             }
          }
       return nameToErase;
    }
    
    public int getLogicalSize()  //allows Interface to access logicalSize
    {
        return logicalSize;
    }
    
    public Movie[] resizeMovieArray(boolean check, Movie[] allMovies)  //increase or decrease the size of an array as appropriate
    {
        Movie[] allMoviesTemp;
        int originalSize = logicalSize;
        int numberOfMovies = allMovies[0].getNumberOfMovies();
        
        if (check == true)
           logicalSize = (logicalSize + 4);
        else
        {
           if (logicalSize>=8)
              logicalSize = (logicalSize/2);
           else
              logicalSize = 4;
        }
        
        allMoviesTemp = new Movie[logicalSize];
        int numberTemp = 0;
        
        for (int number=0; number<logicalSize; number++)
           allMoviesTemp[number] = new Movie();
           
        for (int number=0; number<originalSize; number++)
        {
           if (!allMovies[number].getName().isEmpty())
           {   
              allMoviesTemp[numberTemp] = allMovies[number];
              numberTemp++;
           }
        }
        allMoviesTemp[0].setNumberOfMovies(numberOfMovies);
        return allMoviesTemp;
    }
    
    public int getNumber()  //access numberOfMovies in Movie class
    {
        return allMovies[0].getNumberOfMovies();
    }
    
    public int getLength(int number)  //access runTime in Movie class
    {
        return allMovies[number].getRunTime();
    }
    
    public String addToFile()  //output database to a file
    {
       String newName,newDirector;
       String filename = "database.txt";
       int newFileSize,newRunTime;
       try
       {              
          ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename));
          for (int number=0; number<logicalSize; number++)
          {
             if (!allMovies[number].getName().isEmpty())
             {
                newName = allMovies[number].getName();
                newDirector = allMovies[number].getDirector();
                newFileSize = allMovies[number].getFileSize();
                newRunTime = allMovies[number].getRunTime();
                outputStream.writeObject(allMovies[number]);
             }
          }
          outputStream.close();
       }
       catch(FileNotFoundException e)
       {
          return "Nothing.";
       }
       catch(IOException e)
       {
          return "Nada."; 
       }
       return "Saved.";
    }
    
    public String addFromFile()  //input database information from a file
    {
       boolean stop = true;
       allMovies = resizeMovieArray(stop,allMovies);
       String filename = "movieDatabase.txt";
       Scanner inputStream = null;
       try
       {
          inputStream = new Scanner(new File(filename));
       }
       catch(FileNotFoundException e)
       {
          return "File not found.";
       }
       
          for(int number=0,count=logicalSize; number<logicalSize; number++)
          {
             if (!allMovies[number].getName().isEmpty())
             {
                count++;
                if(count>=4)
                {
                   count=0;
                   stop = true;
                   allMovies = resizeMovieArray(stop,allMovies);
                }
             }
             else
             {                
                try
                {
                   String line;
                   int figure;
                   String name="",director="";
                   int fileSize=0,runTime=0;
                   line = inputStream.nextLine();
                   line = line.replace("Movie title: ","");
                   name = line;
                
                   line = inputStream.nextLine();
                   line = line.replace("Director: ","");
                   director = line;
                
                   line = inputStream.nextLine();
                   line = line.replace("fileSize: ","");
                   figure = Integer.parseInt(line.replaceAll("[^0-9]",""));
                   fileSize = figure;
                
                   line = inputStream.nextLine();
                   line = line.replace("duration: ","");
                   figure = Integer.parseInt(line.replaceAll("[^0-9]",""));
                   runTime = figure;
                   
                   if (cloneMovie(name,director,runTime,fileSize)==false)
                      defineMovie(name,director,runTime,fileSize);
                }
                catch (NumberFormatException e)
                {
                   System.out.println("Fail.");
                }
             }
          }
          
       if (allMovies[0].getNumberOfMovies()*2<logicalSize)
       {
           stop = false;
           resizeMovieArray(stop,allMovies);
       }
       return null;
    }
    
    public void editMovie(String name, String director, int fileSize, int runTime, int select)  //edit a movie in the database
    {
       boolean stop = false;
       allMovies[select].storeMovie(name,director,runTime,fileSize,stop);
    }
   
    public String searchByDirector(int number) // search for movie by director
    {
       return (allMovies[number].getDirector());
    }
   
}

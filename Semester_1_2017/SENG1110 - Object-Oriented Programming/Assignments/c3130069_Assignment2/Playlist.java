/**
   Written by: Sam Dolbel (3130069)
   Last modified: 29/5/2017
   Class: Thursday 11-1pm
 */

public class Playlist
{
    private Movie[] movies, moviesTemp;
    private int totalTime, totalSize, logicalSize;
    private final int MAX_TIME = 1000;
    private final int MAX_SIZE = 20;
    
    public Playlist()
    {
        logicalSize = 4;
        movies = new Movie[logicalSize];
        totalTime = 0;
        totalSize = 0;
        for (int number=0; number<logicalSize; number++)
           movies[number] = new Movie();
    }
    
    public int getLogicalSize() // allows Interface to access logicalSize
    {
        return logicalSize;
    }
    
    public int getTotalTime()  // allows Interface to access totalTime
    {
        return totalTime;
    }
    
    public int getTotalSize()  // allows Interface to access totalSize
    {
        return totalSize;
    }
    
    public boolean emptyPlaylist() // tests whether a given playlist is empty
    {
       for (int number=0; number<logicalSize; number++)
       {
          if (!movies[number].getName().isEmpty())
             return false;
       }
       return true;
    }
    
    public boolean movieToPlaylist(String newName, String newDirector, int newRunTime, int newFileSize) // adds a movie to a playlist
    {
       String name=newName, director=newDirector;
       int runTime=newRunTime, fileSize=newFileSize, number=0;
       boolean stop=false;
       do
       {
          if (movies[number].getName().isEmpty())
          {
             if (maxTest(runTime,totalTime,fileSize,totalSize)==true)
             {
                totalTime = totalTime + runTime;
                totalSize = totalSize + fileSize;
                movies[number].storeMovie(name,director,runTime,fileSize,stop);
                stop = true;
             }
             else
                return false;
          }
          else
          {
             number++;
             if (number==(logicalSize-1))
             {
                 boolean check = true;
                 movies = resizePlayMovieArray(check,movies);
             }
          }
       }
       while (stop==false);
       return true;
    }
    
    public String showPlaylist(int movieNumber) // outputs the playlists
    {
       String newName,newDirector;
       int newFileSize,newRunTime;
       if (movies[movieNumber].getName().isEmpty())
          return ("No data.");
       else
       {
          newName = movies[movieNumber].getName();
          newDirector = movies[movieNumber].getDirector();
          newFileSize = movies[movieNumber].getFileSize();
          newRunTime = movies[movieNumber].getRunTime();
          return ((movieNumber+1)+":   Name: "+newName+"   Director: "+newDirector+"   File Size: "+newFileSize+"   Duration: "+newRunTime);
        }
    }
    
    public boolean removeFromPlaylist(int newSelect) // remove a movie from a playlist
    {
        int select = newSelect;
        boolean confirm;
        for (int number=0; number<logicalSize; number++)
           if (select == number && movies[number].getName() != "")
           {
              movies[number].eraseMovie();
              return true;
           }
        return false;
    }
    
    public void deleteFromPlaylist(String newDelete) // delete a movie from a playlist when it is deleted from the database
    {
        String delete = newDelete;
        for (int number=0; number<logicalSize; number++)
           if (movies[number].getName() == delete)
           {
              movies[number].eraseMovieFromPlaylist();
              if ((logicalSize/2)>(movies[number].getNumberOfMovies()))
              {
                boolean check = false;
                movies = resizePlayMovieArray(check,movies);
              }
           }
    }
    
    public int deletePlaylist(String newDelete) // delete a whole playlist
    {
        String delete = newDelete;
        delete = delete.toLowerCase();
        if (delete.equals("y"))
        {
           for (int number=0; number<logicalSize; number++)
           {
              movies[number].eraseMovieFromPlaylist();
           }
           return 1;
        }
        else if (delete.equals("n"))
        {
           return 2;
        }
        else
        {
           return 3;
        }
    }
    
    public boolean clonePlaylist(String newName,String newDirector,int newRunTime,int newFileSize) // searches for duplicate movie in a playlist
    {
       String name = newName, director = newDirector;
       int runTime = newRunTime, fileSize = newFileSize;
       
       for (int number=0; number<logicalSize; number++)
          if (name.equals(movies[number].getName()) && director.equals(movies[number].getDirector()) && runTime == movies[number].getRunTime())
             return true;
             
       return false;
    }
    
    public Movie[] resizePlayMovieArray(boolean check, Movie[] movies) //increase or decrease the array that comprises a playlist
    {
        int originalSize = logicalSize;
        int numberOfMovies = movies[0].getNumberOfMovies();
        
        if (check == true)
           logicalSize = (logicalSize + 4);
        else
        {
           if (logicalSize>=8)
              logicalSize = (logicalSize/2);
           else
              logicalSize = 4;
        }
        
        moviesTemp = new Movie[logicalSize];
        int numberTemp = 0;
        
        for (int number=0; number<logicalSize; number++)
           moviesTemp[number] = new Movie();
           
        for (int number=0; number<originalSize; number++)
        {
           if (!movies[number].getName().isEmpty())
           {   
              moviesTemp[numberTemp] = movies[number];
              numberTemp++;
           }
        } 
        moviesTemp[0].setNumberOfMovies(numberOfMovies);
        return moviesTemp;
    }
    
    public boolean maxTest(int runTime, int totalTime, int fileSize, int totalSize) // test whether a movie to be added is too long or too large.
    {
        if (((totalSize+fileSize)/1000)>=MAX_SIZE || (totalTime+runTime)>=MAX_TIME)
           return false;
        else
           return true;
    }
}

/**
   Written by: Sam Dolbel (3130069)
   Date: 7/4/2017
   Class: Thursday 11-1pm
 */
import java.util.*;

public class MovieDatabase
{
    private Movie       movie1,movie2,movie3,movie4;
    private Interface   intFace;
    
    public MovieDatabase()
    {   
       movie1 = new Movie();
       movie2 = new Movie();
       movie3 = new Movie();
       movie4 = new Movie();
       intFace = new Interface();
    }
    
    public void defineMovie(String newName,String newDirector,int newRunTime,int newFileSize) // stores a movie within the database.
    {
       String name = newName, director = newDirector;
       int runTime = newRunTime, fileSize = newFileSize;
       if (movie1.getName() == "")
          movie1.storeMovie(name,director,runTime,fileSize);
       else if (movie2.getName() == "")
          movie2.storeMovie(name,director,runTime,fileSize);
       else if (movie3.getName() == "")
          movie3.storeMovie(name,director,runTime,fileSize);
       else if (movie4.getName() == "")
          movie4.storeMovie(name,director,runTime,fileSize);
    }
    
    public boolean cloneMovie(String newName,String newDirector,int newRunTime,int newFileSize) // searches for duplicate movies in a playlist
    {
       String name = newName, director = newDirector;
       int runTime = newRunTime, fileSize = newFileSize;
       
       if (name.equals(movie1.getName()) && director.equals(movie1.getDirector()) && runTime == movie1.getRunTime())
          return true;
       else if (name.equals(movie2.getName()) && director.equals(movie2.getDirector()) && runTime == movie2.getRunTime())
          return true;
       else if (name.equals(movie3.getName()) && director.equals(movie3.getDirector()) && runTime == movie3.getRunTime())
          return true;
       else if (name.equals(movie4.getName()) && director.equals(movie4.getDirector()) && runTime == movie4.getRunTime())
          return true;
       else
          return false;
    }
    
    public void showList() // prints the list of movies stored in the database.
    {
       String newName,newDirector;
       int newFileSize,newRunTime,number;
       if (movie1.getName() != "")
          {
             newName = movie1.getName();
             newDirector = movie1.getDirector();
             newFileSize = movie1.getFileSize();
             newRunTime = movie1.getRunTime();
             number = 1;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
       if (movie2.getName() != "")
          {
             newName = movie2.getName();
             newDirector = movie2.getDirector();
             newFileSize = movie2.getFileSize();
             newRunTime = movie2.getRunTime();
             number = 2;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
       if (movie3.getName() != "")
          {
             newName = movie3.getName();
             newDirector = movie3.getDirector();
             newFileSize = movie3.getFileSize();
             newRunTime = movie3.getRunTime();
             number = 3;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
       if (movie4.getName() != "")
          {
             newName = movie4.getName();
             newDirector = movie4.getDirector();
             newFileSize = movie4.getFileSize();
             newRunTime = movie4.getRunTime();
             number = 4;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
    }
    
    public boolean fullList() // tests whether the database is full
    {  
       boolean full=false;
       if (movie1.getName()!="" && movie2.getName()!="" && movie3.getName()!="" && movie4.getName()!="")
          full = true;
       return full;
    }
    
    public boolean emptyList() // tests whether the database is empty
    {  
       boolean full=false;
       if (movie1.getName()=="" && movie2.getName()=="" && movie3.getName()=="" && movie4.getName()=="")
          full = true;
       return full;
    }
    
    public boolean testForPlaylist(int newSelect) // tests whether a selection represents a valid movie in the database
    {
       int select = newSelect;
       if (select == 1 && movie1.getName()!="")
          return true;
       else if (select == 2 && movie2.getName()!="")
          return true;
       else if (select == 3 && movie3.getName()!="")
          return true;
       else if (select == 4 && movie4.getName()!="")
          return true;
       else
          return false;
    }
    
    public String nameToPlaylist(int newSelect) // outputs a movie title to add to a playlist
    {
       int select = newSelect;
       if (select == 1)
          return movie1.getName();
       else if (select == 2)
          return movie2.getName();
       else if (select == 3)
          return movie3.getName();
       else
          return movie4.getName();
    }
    
    public String directorToPlaylist(int newSelect)  // outputs a director name to add to a playlist
    {
       int select = newSelect;
       if (select == 1)
          return movie1.getDirector();
       else if (select == 2)
          return movie2.getDirector();
       else if (select == 3)
          return movie3.getDirector();
       else
          return movie4.getDirector();
    }
    
    public int runTimeToPlaylist(int newSelect)  // outputs movie length to add to a playlist
    {
       int select = newSelect;
       if (select == 1)
          return movie1.getRunTime();
       else if (select == 2)
          return movie2.getRunTime();
       else if (select == 3)
          return movie3.getRunTime();
       else
          return movie4.getRunTime();
    }
    
    public int fileSizeToPlaylist(int newSelect)  // outputs file size to add to a playlist
    {
       int select = newSelect;
       if (select == 1)
          return movie1.getFileSize();
       else if (select == 2)
          return movie2.getFileSize();
       else if (select == 3)
          return movie3.getFileSize();
       else
          return movie4.getFileSize();
    }
    
    public boolean movieExists(int newSelect)  // tests whether a movie exists
    {
       int select = newSelect;
       if (select == 1 && movie1.getName()!="")
          return true;
       else if (select == 2 && movie2.getName()!="")
          return true;
       else if (select == 3 && movie3.getName()!="")
          return true;
       else if (select == 4 && movie4.getName()!="")
          return true;
       else
          return false;
    }
    
    public String deleteMovie(int newSelect)  // deletes a movie from the database and all playlists
    {
       int select = newSelect;
       String nameToErase;
       if (select == 1 && movie1.getName()!="")
       {
          nameToErase = movie1.getName();
          movie1.eraseMovie();
       }
       else if (select == 2 && movie2.getName()!="")
       {
          nameToErase = movie2.getName();
          movie2.eraseMovie();
       }
       else if (select == 3 && movie3.getName()!="")
       {
          nameToErase = movie3.getName();
          movie3.eraseMovie();
       }
       else
       {
          nameToErase = movie4.getName();
          movie4.eraseMovie();
       }
       return nameToErase;
    }
    public void searchByDirector()
    {
       String director1="director1",director2="director2",director3="director3",director4="director4",newName,newDirector;
       int newFileSize,newRunTime,number;
       
       if (movie1.getName()!="")
          director1 = movie1.getDirector();
       if (movie2.getName()!="")
          director2 = movie2.getDirector();
       if (movie3.getName()!="")
          director3 = movie3.getDirector();
       if (movie4.getName()!="")
          director4 = movie4.getDirector();
          
       if (director1.equals(director2) || director1.equals(director3) || director1.equals(director4))
          {
             newName = movie1.getName();
             newDirector = movie1.getDirector();
             newFileSize = movie1.getFileSize();
             newRunTime = movie1.getRunTime();
             number = 1;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
          
       if (director2.equals(director1) || director2.equals(director3) || director2.equals(director4))
          {
             newName = movie2.getName();
             newDirector = movie2.getDirector();
             newFileSize = movie2.getFileSize();
             newRunTime = movie2.getRunTime();
             number = 2;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
          
       if (director3.equals(director1) || director3.equals(director2) || director3.equals(director4))
          {
             newName = movie3.getName();
             newDirector = movie3.getDirector();
             newFileSize = movie3.getFileSize();
             newRunTime = movie3.getRunTime();
             number = 3;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
          
       if (director4.equals(director1) || director4.equals(director2) || director4.equals(director3))
          {
             newName = movie4.getName();
             newDirector = movie4.getDirector();
             newFileSize = movie4.getFileSize();
             newRunTime = movie4.getRunTime();
             number = 4;
             intFace.movieList(newName,newDirector,newFileSize,newRunTime,number);
          }
    }
}

/**
   Written by: Sam Dolbel (3130069)
   Date: 17/4/2017
   Class: Thursday 11-1pm
 */

public class Playlist
{
    Movie movie1 = new Movie();
    Movie movie2 = new Movie();
    Movie movie3 = new Movie();
    
    public boolean emptyPlaylist() // tests whether a given playlist is empty
    {
       if (movie1.getName()=="" && movie2.getName()=="" && movie3.getName()=="")
          return true;
       else
          return false;
    }
    
    public boolean fullPlaylist() // tests whether a given playlist is full
    {
       if (movie1.getName()!="" && movie2.getName()!="" && movie3.getName()!="")
          return true;
       else
          return false;
    }
    
    public void movieToPlaylist(String newName, String newDirector, int newRunTime, int newFileSize) // adds a movie to a playlist
    {
        String name = newName, director = newDirector;
        int runTime = newRunTime, fileSize = newFileSize;
        
        if (movie1.getName()=="")
           movie1.storeMovie(name,director,runTime,fileSize);
        else if (movie2.getName()=="")
           movie2.storeMovie(name,director,runTime,fileSize);
        else
           movie3.storeMovie(name,director,runTime,fileSize);
    }
    
    public void showPlaylist() // outputs the playlists
    {
       Interface intFace = new Interface();
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
    }
    
    public boolean removeFromPlaylist(int newSelect) // remove a movie from a playlist
    {
        int select = newSelect;
        boolean confirm;
        if (select == 1 && movie1.getName() != "")
        {
           movie1.eraseMovie();
           return true;
        }
        else if (select == 2 && movie2.getName() != "")
        {
           movie2.eraseMovie();
           return true;
        }
        else if (select == 3 && movie3.getName() != "")
        {
           movie3.eraseMovie();
           return true;
        }
        else
           return false;
    }
    
    public void deleteFromPlaylist(String newDelete) // delete a movie from a playlist when it is deleted from the database
    {
        String delete = newDelete;
        if (movie1.getName() == delete)
           movie1.eraseMovie();
        else if (movie2.getName() == delete)
           movie2.eraseMovie();
        else if (movie3.getName() == delete)
           movie3.eraseMovie();
    }
    public int deletePlaylist(String newDelete) // delete a whole playlist
    {
        String delete = newDelete;
        delete = delete.toLowerCase();
        if (delete.equals("y"))
        {
           movie1.eraseMovie();
           movie2.eraseMovie();
           movie3.eraseMovie();
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
       
       if (name.equals(movie1.getName()) && director.equals(movie1.getDirector()) && runTime == movie1.getRunTime())
          return true;
       else if (name.equals(movie2.getName()) && director.equals(movie2.getDirector()) && runTime == movie2.getRunTime())
          return true;
       else if (name.equals(movie3.getName()) && director.equals(movie3.getDirector()) && runTime == movie3.getRunTime())
          return true;
       else
          return false;
    }
}

/**
   Written by: Sam Dolbel (3130069)
   Date: 7/4/2017
   Class: Thursday 11-1pm
 */
public class Movie
{
    private String      name,director;
    private int         fileSize,runTime;
    
    public Movie()
    {
        name = "";
        director = "";
        fileSize = 0;
        runTime = 0;
    }
    
    public void eraseMovie()
    {
        name = "";
        director = "";
        fileSize = 0;
        runTime = 0;
    }
    
    public void storeMovie(String newName, String newDirector, int newRunTime, int newFileSize)
    {
        name = newName;
        director = newDirector;
        fileSize = newFileSize;
        runTime = newRunTime;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDirector()
    {
        return director;
    }
    
    public int getRunTime()
    {
        return runTime;
    }
    
    public int getFileSize()
    {
        return fileSize;
    }
}
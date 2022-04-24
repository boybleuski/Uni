/**
   Written by: Sam Dolbel (3130069)
   Last modified: 29/5/2017
   Class: Thursday 11-1pm
 */
import java.io.*;
public class Movie implements Serializable
{
    private String      name,director;
    private int         fileSize,runTime;
    private static int         numberOfMovies;
    
    public Movie()
    {
        name = "";
        director = "";
        fileSize = 0;
        runTime = 0;
        numberOfMovies = 0;
    }
    
    public void eraseMovie()
    {
        name = "";
        director = "";
        fileSize = 0;
        runTime = 0;
        numberOfMovies--;
    }
    
    public void eraseMovieFromPlaylist()
    {
        name = "";
        director = "";
        fileSize = 0;
        runTime = 0;
    }
    
    public void setNumberOfMovies(int movies)
    {
        numberOfMovies = movies;
    }
    
    public void storeMovie(String newName, String newDirector, int newRunTime, int newFileSize, boolean stop)
    {
        name = newName;
        director = newDirector;
        fileSize = newFileSize;
        runTime = newRunTime;
        if (stop==true)
           numberOfMovies++;
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
    
    public int getNumberOfMovies()
    {
        return numberOfMovies;
    }
}
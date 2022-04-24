/**
   Written by: Sam Dolbel (3130069)
   Last modified: 29/5/2017
   Class: Thursday 11-1pm
   SENG1110 Assignment Part 2 - movie database with functionality of:
    - adding/deleting/editing movies
    - adding to/deleting from/deleting movie playlists
       - database and playlists have no element limits, but playlists have size and length limits
    - inputting from/outputting to a text file
    - requesting lists of movies by director/shorter than a certain duration
 */
import java.util.*;
import java.io.*;

public class Interface
{
   private int logicalSize = 2;
   
   public void run()
   {
      int option,fileSize=0,runTime=0,select,number=0;
      boolean quit=false,confirm=false,loaded=false;
      String name="",director="",delete="",filename, result;
      Scanner keyboard = new Scanner(System.in);
      MovieDatabase database = new MovieDatabase();
      Playlist[] playlists = new Playlist[logicalSize];
      for (number=0; number<logicalSize; number++)
         playlists[number] = new Playlist();
         
      do
      {
         System.out.println("\nPlease make a selection (1-13):");
         System.out.println("(1)Show movie list   (2)Add new movie   (3)Delete movie\n(4)Show playlists   (5)Add movie to playlist\n(6)Delete movie from playlist   (7)Delete playlist");
         System.out.println("(8)Request movie list by length   (9)Edit movie in database\n(10)Save database   (11)Load database   (12)Search by director\n(13)Quit"); 
         try
         {
            option = Integer.parseInt(keyboard.nextLine());
         }
         catch(NumberFormatException e)
         {
            option = 0;
         }
         
         switch(option)
            {
               case 1:  //Print a list of all movies in the database
                  for (number=0; number<(database.getLogicalSize()); number++)
                     System.out.println(database.showList(number));
                  break;
                 
               case 2:  //Add a new movie to the database
                 name="";
                 director="";
                 fileSize=0;
                 runTime=0;
                 
                 while (name.isEmpty())
                 {
                    System.out.println("Enter the name of the movie.");
                    name = keyboard.nextLine();
                    name = name.toUpperCase();
                    if (name.isEmpty())
                       System.out.println("The movie has to have a name.");
                 }
                 
                 while (director.isEmpty())
                 {
                    System.out.println("Who was the director?");
                    director = keyboard.nextLine();
                    director = director.toUpperCase();
                    if (director.isEmpty())
                       System.out.println("The movie has to have a director.");
                 }
                   
                 while (runTime <= 0)
                 {
                    try
                    {
                       System.out.println("How long is "+name+", in minutes?");
                       runTime = Integer.parseInt(keyboard.nextLine());
                       if (runTime <= 0)
                          System.out.println("The movie has to have a duration.");
                    }
                    catch(NumberFormatException e)
                    {
                       System.out.println("Number values only, please.");
                    }
                 }
                 
                 while (fileSize <= 0)
                 {
                    try
                    {
                       System.out.println("How large is "+name+", in megabytes?");
                       fileSize = Integer.parseInt(keyboard.nextLine());
                       if (fileSize <= 0)
                          System.out.println("The movie has to have a file size.");
                    }
                    catch(NumberFormatException e)
                    {
                       System.out.println("Number values only, please.");
                    }
                 }
                 
                 if (database.cloneMovie(name,director,runTime,fileSize)==false)
                    database.defineMovie(name,director,runTime,fileSize);
                 else
                    System.out.println("That movie is already in the database.");
                 
                 break;
                 
               case 3: //delete a movie from the database
                  if (database.emptyList()==true)
                     System.out.println("There are no movies to delete.");
                  else
                  {
                     System.out.println("Which movie would you like to delete?");
                     for (number=0; number<(database.getLogicalSize()); number++)
                        System.out.println(database.showList(number));
                     System.out.println("Enter the number of the movie that you want to delete.");
                     try
                     {
                        select = Integer.parseInt(keyboard.nextLine());
                     }
                     catch (NumberFormatException e)
                     {
                        System.out.println("That was not even a number.");
                        break;
                     }
                     confirm = database.movieExists(select);
                     
                     if (confirm == true)
                     {
                        delete = database.deleteMovie(select);
                        for (number=0; number<logicalSize; number++)
                           playlists[number].deleteFromPlaylist(delete);
                        System.out.println("Deleted.");
                     }
                     else
                        System.out.println(delete);
                  }
                  break;
                  
               case 4:  //displays the stored playlists
                  for (number=0; number<logicalSize; number++)
                  {
                     if (playlists[number].emptyPlaylist() == false)
                     {
                        System.out.println("Playlist " +(number+1)+": ");
                        for (int movieNumber=0; movieNumber<(playlists[number].getLogicalSize()); movieNumber++)
                           System.out.println(playlists[number].showPlaylist(movieNumber));
                        System.out.println("Total Duration: "+playlists[number].getTotalTime()+" minutes\nTotal Size: "+(playlists[number].getTotalSize())/1000+"GB\n");
                     }
                  }
                  break;
                  
               case 5:  //Add movies to a playlist
                  confirm = true;
                  for(number=0; number<logicalSize; number++)
                  {
                     if (playlists[number].emptyPlaylist() == true)
                        confirm = false;
                  }
                  
                  if (confirm == true)
                     playlists = resizePlaylistArray(confirm, logicalSize, playlists);
                     
                  if (database.emptyList() == true)
                     System.out.println("There are no movies to add to playlists.");
                  else 
                  {
                     for (number=0; number<(database.getLogicalSize()); number++)
                        System.out.println(database.showList(number));
                     System.out.println("Enter the number of the movie that you want to add to a playlist.");
                     try
                     {
                        select = Integer.parseInt(keyboard.nextLine());
                     }
                     catch (NumberFormatException e)
                     {
                        System.out.println("That was not even a number.");
                        break;
                     }
                        
                     confirm = database.testForPlaylist(select);
                     if (confirm == true)
                     {
                        name = database.nameToPlaylist(select);
                        director = database.directorToPlaylist(select);
                        runTime = database.runTimeToPlaylist(select);
                        fileSize = database.fileSizeToPlaylist(select);
                        System.out.println("Add to which playlist?");
                          
                        for (number=0; number<logicalSize; number++)
                        {
                           if (playlists[number].emptyPlaylist() == true)
                              System.out.println("Playlist "+(number+1)+": Empty");
                           else
                           {
                              System.out.println("Playlist " +(number+1)+": ");
                              for (int movieNumber=0; movieNumber<(playlists[number].getLogicalSize()); movieNumber++)
                                 System.out.println(playlists[number].showPlaylist(movieNumber));
                           }
                        }
                        
                        try
                        {
                           select = Integer.parseInt(keyboard.nextLine());
                           select = select - 1;
                        }
                        catch (NumberFormatException e)
                        {
                           System.out.println("That was not even a number.");
                           break;
                        }
                        
                        if (select>logicalSize || select < 0)
                        {
                            System.out.println("Invalid selection.");
                            break;
                        }
                        
                        if (playlists[select].clonePlaylist(name,director,runTime,fileSize) == false)
                        {
                           if (playlists[select].movieToPlaylist(name,director,runTime,fileSize) == true)
                              System.out.println("Added to playlist "+(select+1)+".");
                           else
                              System.out.println("Exceeded the duration or file size limit.");
                        }
                        else
                            System.out.println("That movie is already in the playlist.");
                     }
                     else
                        System.out.println("That was not one of the options.");
                  }
                  break;
                  
               case 6: //delete movie from a playlist
                  System.out.println("Delete from which playlist?");
                  select=0;
                  for (number=0; number<logicalSize; number++)
                  {
                     if (playlists[number].emptyPlaylist() == false)
                     {
                        System.out.println("Playlist "+(number+1)+": ");
                        for (int movieNumber=0; movieNumber<(playlists[number].getLogicalSize()); movieNumber++)//range from 0 to the logical end of the playlist
                           System.out.println(playlists[number].showPlaylist(movieNumber));
                     }
                  }
                  try
                  {
                     select = Integer.parseInt(keyboard.nextLine());
                     select--;
                     if (playlists[select].emptyPlaylist() == true)
                        System.out.println("That is an empty playlist.");
                     else if (select < 0 || select > logicalSize)
                        System.out.println("That is an invalid selection.");
                  }
                  catch (NumberFormatException e)
                  {
                     System.out.println("Your grasp of numbers is lacking.");
                     break;
                  }
                  int playlistKey = select;                       
                  System.out.println("Which movie would you like to delete?");
                  for (number=0; number<logicalSize; number++)
                  {
                     if (playlists[number].emptyPlaylist() == false)
                     {
                        System.out.println("Playlist " +(number+1)+": ");
                        for (int movieNumber=0; movieNumber<(playlists[number].getLogicalSize()); movieNumber++)
                           System.out.println(playlists[number].showPlaylist(movieNumber));
                     }
                  }
                  System.out.println("Enter the number of the movie that you\nwant to delete from the playlist.");
                  try
                  {
                     select = Integer.parseInt(keyboard.nextLine());
                     select--;
                  }
                  catch (NumberFormatException e)
                  {
                      System.out.println("If I only had a number...");
                  }
                  
                  playlists[playlistKey].removeFromPlaylist(select);
                  if (confirm == true)
                     System.out.println("Removed from playlist.");
                  else
                     System.out.println("That was not a valid option.");
                  break;
                  
               case 7: //deletes a whole playlist
                  System.out.println("Delete which playlist?");
                  for (number=0; number<logicalSize; number++)
                  {
                     if (playlists[number].emptyPlaylist() == false)
                     {
                        System.out.println("Playlist "+(number+1)+": ");
                        for (int movieNumber=0; movieNumber<(playlists[number].getLogicalSize()); movieNumber++)
                           System.out.println(playlists[number].showPlaylist(movieNumber));
                     }
                  }
                  
                  try
                  {
                     select = Integer.parseInt(keyboard.nextLine());
                     select--; //so that the "0" element is used
                  }
                  catch (NumberFormatException e)
                  {
                     System.out.println("What do you have against numbers?");
                     break;
                  }
                  
                  if (playlists[select].emptyPlaylist() == true)
                     System.out.println("That is an empty playlist.");
                  else if (select < 0 || select > logicalSize)
                     System.out.println("That is an invalid selection.");
                     
                  System.out.println("Really delete playlist "+(select+1)+"? Y/N");  //something this significant should require confirmation
                  delete = keyboard.nextLine();
                  number = playlists[select].deletePlaylist(delete);
                  
                  if (number == 1)
                     System.out.println("Playlist "+(select+1)+" deleted.");
                  if (number == 2)
                     System.out.println("Playlist "+(select+1)+" spared.");
                  if (number == 3)
                     System.out.println("That was not a valid option.");
                     
                  int count=0;
                  for(number=0; number<logicalSize; number++)  
                  {
                      if(playlists[number].emptyPlaylist()==false)
                         count++;
                  }
                  if (count<(logicalSize/2))
                  {
                      confirm = false;
                      resizePlaylistArray(confirm,logicalSize,playlists);
                  }
                  break;
                  
               case 8: //search for movies under a certain user-defined duration
                  int minuteValue;
                  System.out.println("Enter a value in minutes.");
                  try
                  {
                     minuteValue = Integer.parseInt(keyboard.nextLine());
                  }
                  catch (NumberFormatException e)
                  {
                     System.out.println("A value in minutes implies a number.");
                     break;
                  }
                  
                  for(number=0; number<(database.getLogicalSize()); number++)
                  {
                     if (database.getLength(number) < minuteValue)
                        System.out.println(database.showList(number));
                  }
                  break;
                  
               case 9:  //edit a movie in the database
                  for (number=0; number<(database.getLogicalSize()); number++)
                     System.out.println(database.showList(number));
                  System.out.println("Enter the number of the movie that you want to edit.");
                  
                  try
                  {
                     select = Integer.parseInt(keyboard.nextLine());
                     select--;
                  }
                  catch (NumberFormatException e)
                  {
                     System.out.println("That was not even a number.");
                     break;
                  }
                  
                  confirm = database.movieExists(select);
                  if(confirm == true)
                  {
                     name="";
                     director="";
                     fileSize=0;
                     runTime=0;
                 
                     while (name.isEmpty())
                     {
                        System.out.println("Name?");
                        name = keyboard.nextLine();
                        name = name.toUpperCase();
                        if (name.isEmpty())
                           System.out.println("The movie has to have a name.");
                     }
                 
                     while (director.isEmpty())
                     {
                        System.out.println("Director?");
                        director = keyboard.nextLine();
                        director = director.toUpperCase();
                        if (director.isEmpty())
                           System.out.println("The movie has to have a director.");
                     }
                   
                     while (runTime <= 0)
                     {
                        try
                        {
                           System.out.println("Duration in minutes?");
                           runTime = Integer.parseInt(keyboard.nextLine());
                           if (runTime <= 0)
                              System.out.println("The movie has to have a duration.");
                        }
                        catch(NumberFormatException e)
                        {
                           System.out.println("Number values only, please.");
                        }
                     }
                 
                     while (fileSize <= 0)
                     {
                        try
                        {
                           System.out.println("File size in MB?");
                           fileSize = Integer.parseInt(keyboard.nextLine());
                           if (fileSize <= 0)
                              System.out.println("The movie has to have a file size.");
                        }
                        catch(NumberFormatException e)
                        {
                           System.out.println("Number values only, please.");
                        }
                     }
                 
                     if (database.cloneMovie(name,director,runTime,fileSize)==false)
                        database.editMovie(name,director,runTime,fileSize,select);
                     else
                        System.out.println("That movie is already in the database.");
                  }
                  break;
                  
               case 10: //output database to a file
                  result = database.addToFile();
                  System.out.println(result);
                  break;
                  
               case 11: //input database from a file and add to existing database
                  if(loaded == false)
                  {
                     result = database.addFromFile();
                     System.out.println(result);
                     loaded = true;
                  }
                  else
                     System.out.println("File already loaded.");
                  break;
                  
               case 12: //search for movies by director
                  System.out.println("Enter the name of the director.");
                  director = keyboard.nextLine();
                  director = director.toUpperCase();
                  if (director.isEmpty())
                     System.out.println("I need a director to search for.");
                  else
                  {
                     for(number=0; number<database.getLogicalSize(); number++)
                     {
                        String compare = database.searchByDirector(number);
                        if (compare.equals(director))
                        {
                           System.out.println(database.showList(number));
                           System.out.println(compare);
                           System.out.println(director);
                        }
                     }
                  }
                  break;
                  
               case 13: //quit
                  System.out.println("Quitting...");
                  quit = true;
                  break;
                  
               default: //for those who can't follow instructions
                  System.out.println("Invalid option.");
                  break;
           }
        }
        while (quit == false);
   }
   
   public static void main(String[]args)
   {
      Interface intFace = new Interface();
      intFace.run();
   }
   
   public void setLogicalSize(int newLogicalSize)
   {
      logicalSize = newLogicalSize;    
   }
   
   public Playlist[] resizePlaylistArray(boolean check, int logicalSize, Playlist[] playlists) //increase or decrease as appropriate the number of playlists
   { 
      int originalSize = logicalSize; 
      if (check == true)
         logicalSize = (logicalSize + 2);
      else
      {
         if (logicalSize>=4)
            logicalSize = (logicalSize/2);
         else
            logicalSize = 2;
      }
      
      setLogicalSize(logicalSize);
      Playlist[] playlistsTemp = new Playlist[logicalSize];
      
      for (int number=0; number<logicalSize; number++)
         playlistsTemp[number] = new Playlist();
         
      if (check=true)
      {
         for (int number=0; number<originalSize; number++)
            playlistsTemp[number] = playlists[number];
      }
      else
      {
         for (int number=0; number<logicalSize; number++)
            playlistsTemp[number] = playlists[number];
      }
        
      return playlistsTemp;
   }
}

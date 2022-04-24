/**
   Written by: Sam Dolbel (3130069)
   Date: 7/4/2017
   Class: Thursday 11-1pm
 */
import java.util.*;

public class Interface
{
   public void run()
   {
      int option,fileSize=0,runTime=0,select;
      boolean quit=false,confirm;
      String name="",director="",delete="";
      Scanner keyboard = new Scanner(System.in);
      MovieDatabase database = new MovieDatabase();
      Playlist playlist1 = new Playlist();
      Playlist playlist2 = new Playlist();
      
      do
      {
         System.out.println("\nPlease make a selection (1-9):");
         System.out.println("(1)Show movie list   (2)Add new movie   (3)Delete movie\n(4)Show playlists   (5)Add movie to playlist\n(6)Delete movie from playlist   (7)Delete playlist\n(8)List movies with same director   (9)Quit"); 
         option = keyboard.nextInt();
         keyboard.nextLine();
         switch(option)
            {
               case 1:
               database.showList();           
               break;
                 
               case 2:
                 if (database.fullList()==true)
                 {
                    System.out.println("You cannot add any more movies.  You must delete one first.\n");
                    break;
                 }
                 name="";
                 director="";
                 fileSize=0;
                 runTime=0;
                 
                 System.out.println("Enter the name of the movie.");
                 name = keyboard.nextLine();
                 name = name.toUpperCase();
                 if (name.isEmpty())
                    {
                       System.out.println("The movie has to have a name.");
                       break;
                    }
                 
                 System.out.println("Who was the director?");
                 director = keyboard.nextLine();
                 director = director.toUpperCase();
                 if (director.isEmpty())
                    {
                       System.out.println("The movie has to have a director.");
                       break;
                    }
                   
                 while (runTime <= 0)
                 {
                    System.out.println("How long is "+name+", in minutes?");
                    runTime = keyboard.nextInt();
                    if (runTime <= 0)
                       System.out.println("The movie has to have a duration.");
                 }
                 
                 while (fileSize <= 0)
                 {
                    System.out.println("How large is "+name+", in megabytes?");
                    fileSize = keyboard.nextInt();   
                    if (fileSize <= 0)
                       System.out.println("The movie has to have a file size.");
                 }
                 
                 if (database.cloneMovie(name,director,runTime,fileSize)==false)
                    database.defineMovie(name,director,runTime,fileSize);
                 else
                    System.out.println("That movie is already in the database.");
                 break;
                 
               case 3:
                  if (database.emptyList()==true)
                     System.out.println("There are no movies to delete.");
                  else
                  {
                     System.out.println("Which movie would you like to delete?");
                     database.showList();
                     System.out.println("Enter the number of the movie that you want to delete.");
                     select = keyboard.nextInt();
                     confirm = database.movieExists(select);
                     if (confirm == true)
                     {
                        delete = database.deleteMovie(select);
                        playlist1.deleteFromPlaylist(delete);
                        playlist2.deleteFromPlaylist(delete);
                        System.out.println("Deleted.");
                     }
                     else
                        System.out.println("Invalid option.");
                  }
                  break;
                  
               case 4:
                  if (playlist1.emptyPlaylist() == true && playlist2.emptyPlaylist() == true)
                     System.out.println("The playlists are empty.");
                  else
                  {
                     if (playlist1.emptyPlaylist() == false)
                     {
                        System.out.println("Playlist 1:");
                        playlist1.showPlaylist();
                     }
                     if (playlist2.emptyPlaylist() == false)
                     {
                        System.out.println("Playlist 2:");
                        playlist2.showPlaylist();
                     }
                  }
                  break;
                  
               case 5:
                  if (database.emptyList() == true)
                     System.out.println("There are no movies to add to playlists.");
                  else if (playlist1.fullPlaylist() == true && playlist2.fullPlaylist() == true)
                     System.out.println("The playlists are full.");
                  else 
                     {
                        database.showList();
                        System.out.println("Enter the number of the movie that you want to add to a playlist.");
                        select = keyboard.nextInt();
                        confirm = database.testForPlaylist(select);
                        if (confirm == true)
                        {
                           name = database.nameToPlaylist(select);
                           director = database.directorToPlaylist(select);
                           runTime = database.runTimeToPlaylist(select);
                           fileSize = database.fileSizeToPlaylist(select);
                           confirm = playlist1.fullPlaylist();
                           if (confirm == true)
                           {
                              if (playlist2.clonePlaylist(name,director,runTime,fileSize) == false)
                              {
                                 playlist2.movieToPlaylist(name,director,runTime,fileSize);
                                 System.out.println("Added to playlist 2.");
                              }
                              else
                                 System.out.println("That movie is already in the playlist.");
                           }
                           else
                           {
                              confirm = playlist2.fullPlaylist();
                              if (confirm == true)
                              {
                                 if (playlist1.clonePlaylist(name,director,runTime,fileSize) == false)
                                 {
                                    playlist1.movieToPlaylist(name,director,runTime,fileSize);
                                    System.out.println("Added to playlist 1.");
                                 }
                                 else
                                    System.out.println("That movie is already in the playlist.");
                              }
                              else
                              {
                                 System.out.println("1 or 2?");
                                 select = keyboard.nextInt();
                                 if (select == 1)
                                 {
                                    if (playlist1.clonePlaylist(name,director,runTime,fileSize) == false)
                                    {
                                       playlist1.movieToPlaylist(name,director,runTime,fileSize);
                                       System.out.println("Added to playlist 1.");
                                    }
                                    else
                                       System.out.println("That movie is already in the playlist.");
                                 }
                                 else if (select == 2)
                                 {
                                    if (playlist2.clonePlaylist(name,director,runTime,fileSize) == false)
                                    {
                                       playlist2.movieToPlaylist(name,director,runTime,fileSize);
                                       System.out.println("Added to playlist 2.");
                                    }
                                    else
                                       System.out.println("That movie is already in the playlist.");
                           
                                 }
                                 else
                                    System.out.println("That was not one of the options.");
                              }
                            }
                        }
                        else
                           System.out.println("That was not a valid selection.");
                     }
                  break;
                  
               case 6:
                  if (playlist1.emptyPlaylist() == true && playlist2.emptyPlaylist() == true)
                     System.out.println("The playlists are empty.");
                  else if (playlist1.emptyPlaylist() == false && playlist2.emptyPlaylist() == true)
                  {
                     System.out.println("Which movie would you like to delete?");
                     playlist1.showPlaylist();
                     System.out.println("Enter the number of the movie that you\nwant to delete from the playlist.");
                     select = keyboard.nextInt();
                     playlist1.removeFromPlaylist(select);
                     System.out.println("Removed from playlist.");
                  }
                  else if (playlist1.emptyPlaylist() == true && playlist2.emptyPlaylist() == false)
                  {
                     System.out.println("Which movie would you like to delete?");
                     playlist2.showPlaylist();
                     System.out.println("Enter the number of the movie that you\nwant to delete from the playlist.");
                     select = keyboard.nextInt();
                     playlist2.removeFromPlaylist(select);
                     System.out.println("Removed from playlist.");
                  }
                  else
                  {
                     System.out.println("Playlist 1 or 2?");
                     select = keyboard.nextInt();
                     if (select == 1)
                     {
                        System.out.println("Which movie would you like to delete?");
                        playlist1.showPlaylist();
                        System.out.println("Enter the number of the movie that you\nwant to delete from the playlist.");
                        select = keyboard.nextInt();
                        confirm = playlist1.removeFromPlaylist(select);
                        if (confirm == true)
                           System.out.println("Removed from playlist.");
                        else
                           System.out.println("That was not a valid option.");
                     }
                     else if (select == 2)
                     {
                        System.out.println("Which movie would you like to delete?");
                        playlist2.showPlaylist();
                        System.out.println("Enter the number of the movie that you\nwant to delete from the playlist.");
                        select = keyboard.nextInt();
                        playlist2.removeFromPlaylist(select);
                        System.out.println("Removed from playlist.");
                     }
                     else
                        System.out.println("That was not a valid selection.");
                  }
                  break;
                  
               case 7:
                  if (playlist1.emptyPlaylist() == true && playlist2.emptyPlaylist() == true)
                     System.out.println("There are no playlists to delete.");
                  else if (playlist1.emptyPlaylist() == false && playlist2.emptyPlaylist() == true)
                  {
                     System.out.println("Really delete playlist 1? Y/N");
                     delete = keyboard.nextLine();
                     select = playlist1.deletePlaylist(delete);
                     if (select == 1)
                        System.out.println("Playlist 1 deleted.");
                     if (select == 2)
                        System.out.println("Playlist 1 spared.");
                     if (select == 3)
                        System.out.println("That was not a valid option.");
                  }
                  else if (playlist1.emptyPlaylist() == true && playlist2.emptyPlaylist() == false)
                  {
                     System.out.println("Really delete playlist 2? Y/N");
                     delete = keyboard.nextLine();
                     select = playlist2.deletePlaylist(delete);
                     if (select == 1)
                        System.out.println("Playlist 2 deleted.");
                     if (select == 2)
                        System.out.println("Playlist 2 spared.");
                     if (select == 3)
                        System.out.println("That was not a valid option.");
                  }
                  else
                  {
                     System.out.println("Playlist 1 or 2?");
                     select = keyboard.nextInt();
                     keyboard.nextLine();
                     if (select == 1)
                     {
                        System.out.println("Really delete playlist 1? Y/N");
                        delete = keyboard.nextLine();
                        select = playlist1.deletePlaylist(delete);
                        if (select == 1)
                           System.out.println("Playlist 1 deleted.");
                        if (select == 2)
                           System.out.println("Playlist 1 spared.");
                        if (select == 3)
                           System.out.println("That was not a valid option.");
                     }
                     else if (select == 2)
                     {
                        System.out.println("Really delete playlist 2? Y/N");
                        delete = keyboard.nextLine();
                        select = playlist2.deletePlaylist(delete);
                        if (select == 1)
                           System.out.println("Playlist 2 deleted.");
                        if (select == 2)
                           System.out.println("Playlist 2 spared.");
                        if (select == 3)
                           System.out.println("That was not a valid option.");
                     }
                     else
                        System.out.println("That was not a valid option.");
                  }
                  break;
               case 8:
                  database.searchByDirector();
                  break;
               case 9:
                  System.out.println("Quitting...");
                  quit = true;
                  break;
               default: 
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
      
  public void movieList(String newName, String newDirector, int newFileSize, int newRunTime, int newNumber)
  {
     String name=newName,director=newDirector;
     int fileSize=newFileSize, runTime=newRunTime, number=newNumber;
     System.out.println(number+":  Name: "+name+"  Director: "+director+"  Duration: "+runTime+" minutes  File Size: "+fileSize+"MB"  );
  }
}

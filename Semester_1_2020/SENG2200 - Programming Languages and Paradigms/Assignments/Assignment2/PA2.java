/**
 * The main class of Assignment 2 - SENG2200
 *
 * Author: Sam Dolbel
 * Date created: 18/3/2020
 * Date modified: 15/5/2020
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PA2
{
  public static void main(String[] args)
  {
    File file = new File(args[0]);	// define a file - filename is current directory + the user's input
    Scanner fileScan = null;	// the file scanner
    try
    {
      fileScan = new Scanner(file);
    }
    catch (FileNotFoundException ex)	// only allow existing files
    {
      System.out.println("File not found!");
      System.exit(0);	// abort program if filename is invalid
    }

    LinkedList shapeList = new LinkedList();	// the list of unsorted shapes
    while(fileScan.hasNext())	// check if file contains anymore non-whitespace characters
    {
      String next = fileScan.next();
      if (next.equals("P"))	// only valid character at this stage is "P", which defines a new polygon
      {
        int pointCount = fileScan.nextInt();	// next integer is the number of points of the polygon
        Polygon newShape = new Polygon(pointCount+1);	// number of points + 1
        Point[] points = new Point[pointCount+1];
        for (int i=0; i<pointCount; i++)
          points[i] = new Point(fileScan.nextDouble(), fileScan.nextDouble());	// read the x & y coordinates and create the new point

        points[pointCount] = points[0]; // the last point of the polygon is a copy of the first
        newShape.AddPoints(points);	    // add the array of points to the polygon
        shapeList.Append(newShape);	    // add the polygon to the linked list
      }
      else if (next.equals("C"))
      {
        Point originPoint = new Point(fileScan.nextDouble(), fileScan.nextDouble());  // read the x & y coordinates and create the origin point
        double radius = fileScan.nextDouble(); // radius of the circle
        Circle newShape = new Circle(originPoint, radius);
        shapeList.Append(newShape);	    // add the circle to the linked list
      }
      else if (next.equals("S"))
      {
        Point originPoint = new Point(fileScan.nextDouble(), fileScan.nextDouble());  // read the x & y coordinates and create the origin point
        Point outerPoint = new Point(fileScan.nextDouble(), fileScan.nextDouble());   // read the x & y coordinates and create the intersecting point
        SemiCircle newShape = new SemiCircle(originPoint, outerPoint);
        shapeList.Append(newShape);	    // add the semi-circle to the linked list
      }
    }

    shapeList.Reset();	// return to the start of the list (sentinel node)
    System.out.println("\nUnsorted list");
    for (int i=0; i<shapeList.Count(); i++)	// iterate through the linked list - print the entire unsorted list of polygons
    {
      System.out.println(shapeList.GetCurrent().ToString());	// output the polygon (as a string) to the console
      shapeList.Next();	// get the next node in the list
    }

    LinkedList sortedList = new LinkedList();	// the list of polygons sorted by size
    shapeList.Reset();	// return to the sentinel node
    do
    {
      PlanarShape shape = shapeList.Remove();	// take a node from the front of the unsorted list

      int sortedCount = sortedList.Count();	// count the number of polygons in the sorted list
      if (sortedCount == 0)
        sortedList.Append(shape);	// if the list is empty, Append() can start a new list
      else
      {
        boolean inserted = false;	// determines whether the node taken from the unsorted list has been inserted into the sorted list
        do
        {
          if (shape.ComesBefore(sortedList.GetCurrent()))	// check if the new polygon to be inserted is smaller than the currently selected polygon
          {
            if (shape.Area() < sortedList.GetSentinel().Area())
              sortedList.Prepend(shape);	// prepend to the start of the list if the new polygon is smaller than any polygon in the list
            else
              sortedList.Insert(shape);	    // insert between the current and previous node if it is neither the smallest nor the largest

            inserted = true;	// polygon has been inserted
          }
          else if (sortedList.GetCurrent().GetNext() == sortedList.GetSentinel())	// check if current node is at the end of the list and is therefore the largest
          {
            sortedList.Append(shape);	// append to the end of the list is the new polygon is larger than any polygon in the list
            inserted = true;	// polygon has been inserted
          }
          else
            sortedList.Next();	// move to the next node if the new polygon cannot be inserted yet
          Debug(sortedList, shape);
        }
        while (inserted == false);
        sortedList.Reset();
      }
    }
    while(shapeList.Count() > 0);	// keep going until the unsorted list is empty

    sortedList.Reset();	// return to the sentinel node
    System.out.println("\nSorted list");
    for (int i=0; i<sortedList.Count(); i++)	// iterate through the linked list - print the entire sorted list of polygons
    {
      System.out.println(sortedList.GetCurrent().ToString());	// output the polygon (as a string) to the console
      sortedList.Next();	// get the next node in the list
    }
  }

  public static void Debug(LinkedList list, PlanarShape shape)
  {
    //System.out.println("Current Area: " + list.GetCurrent().Area() + "   Current Distance: " + list.GetCurrent().OriginDistance());
    //System.out.println("New Area: " + shape.Area() + "   New Distance: " + shape.OriginDistance());
    //System.out.println("Current Shape: " + list.GetCurrent().ToString());
    //System.out.println("Next Shape: " + list.GetCurrent().GetNext().ToString());
    for (int i=0; i<list.Count(); i++)	// iterate through the linked list - print the entire sorted list of polygons
    {
      System.out.println(list.GetCurrent().ToString());	// output the polygon (as a string) to the console
      list.Next();	// get the next node in the list
    }
  }
}
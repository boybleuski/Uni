/**
 * Defines the LinkedList class, a circular doubly-linked list of
 * PlanarShapes.
 *
 * Author: Sam Dolbel
 * Date created: 19/3/2020
 * Date modified: 23/3/2020
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class LinkedList
{
  PlanarShape sentinel;	// the first and last node in the list
  PlanarShape current;	// the currently selected node in the list
  int count;	// the number of shapes in the list

  public LinkedList()
  {
    count = 0;
  }

  public int Count()	// get number of shapes
  {
    return count;
  }

  public PlanarShape GetCurrent()	// get currently selected node
  {
    return current;
  }

  public PlanarShape GetSentinel()	// get the sentinel node, the first node in the list
  {
    return sentinel;
  }

  public void Prepend(PlanarShape _shape)	// add a node to the start of the list - this will be the new sentinel node
  {
    if (count == 0)
    {
      Append(_shape);	// if list is empty, append the shape rather than prepend or insert
    }
    else
    {
      PlanarShape shape = _shape;	// create a new shape
      shape.SetPrevious(sentinel.GetPrevious());	// previous node of the new sentinel node is the end of the list
      sentinel.SetPrevious(shape);	// previous node of the old sentinel node is the new sentinel node
      if (count == 1)
        sentinel.SetNext(shape);	// if list only contains 2 nodes, next node of the old sentinel node is the new node

      shape.SetNext(sentinel); //next node of the new sentinel node is the old sentinel node
      sentinel = shape;	// make new node the sentinel node
      //Reset();	// return current node to the start of the list
      count++;	// new shape added
    }
  }

  public void Append(PlanarShape _shape)
  {
    PlanarShape shape = _shape;
    if (count == 0)	// if list is empty, add the shape
    {
      shape.SetNext(shape);	// if list count = 1, next and previous nodes to sentinel node are still the sentinel node
      shape.SetPrevious(shape);
      sentinel = shape;	// new node becomes sentinel node
    }
    else
    {
      shape.SetPrevious(sentinel.GetPrevious());	// previous node of new node is the old end of the list
      sentinel.GetPrevious().SetNext(shape);	// next node of the old end node is the new node
      sentinel.SetPrevious(shape);	// previous node of the sentinel node is the new node - making the new node the end node
      if (count == 1)
        sentinel.SetNext(shape);	// if list only contains 2 nodes, next node of the sentinel node is the new node

      shape.SetNext(sentinel);	// next node of the new node is the sentinel node
    }
	//Reset();	// return current node to the start of the list
    count++;	// new shape added
  }

  public void Insert(PlanarShape _shape)
  {
    PlanarShape temp = current.GetPrevious();	// get a temporary reference to the node before the current node for convenience
    PlanarShape shape = _shape;
    shape.SetPrevious(temp); 	// previous node of the new node is the node that used to be previous to the current node
    current.SetPrevious(shape);	// previous node of the current node is the new node
    temp.SetNext(shape);	    // next node of the node formerly previous to the current node is the new node
    shape.SetNext(current);	    // next node of the new node is the current node
    count++;	// new shape added
  }

  public void Next()	// make current node the next node in the list
  {
    current = current.GetNext();
  }

  public void Reset()	// return the current node to the beginning of the list
  {
    current = sentinel;
  }

  public PlanarShape Remove()	// remove the node from the front of the list
  {
    Reset();	// return current node to the start of the list
    PlanarShape removedShape = sentinel;
    if(sentinel.GetNext() != null) // check if sentinel node is the last node
    {
        Next();	// move to the next node
        sentinel.GetPrevious().SetNext(current);	// next node of the last node becomes the second node in the list
        current.SetPrevious(sentinel.GetPrevious());	// previous node of the second node becomes the last node in the list
        sentinel = null;	// old sentinel is removed
        sentinel = current;	// second node becomes new sentinel
    }
    else	// list is now empty - remove all nodes
    {
        sentinel = null;
        current = null;
    }
    count--;	// shape deleted from list
    return removedShape;	// return the removed shape
  }

  public String Debug()
  {
    current = sentinel;
    String output = "";
    for (int i=0; i<count; i++)
    {
      output = output + " \n " + current.ToString();
      current = current.GetNext();
    }
    output = output + "\n\n";
    return output;
  }
}

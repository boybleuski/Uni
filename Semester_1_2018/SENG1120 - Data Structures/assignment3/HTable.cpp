/*
    HTable.cpp

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 9/6/2018
    Description: Defines the HTable class, a hash table.
*/

#include <cstdlib>
#include <iostream>
#include <string>
#include <sstream>
#include "HTable.h"

HTable::HTable()
{
  positionsFilled = 0;
  for (int i=0; i<150; i++)
    hashArray[i] = 0;
}

HTable::~HTable()
{
  positionsFilled = 0;
  for (int i=0; i<150; i++)
    hashArray[i] = 0;
}

int HTable::get_position() const
{
  return positionsFilled;
}

int HTable::hashfun(int value) //hash function to determine where to place the value in the array
{
  return value%150;
}

void HTable::add(int value) //add a value to the hash table
{
  hashArray[hashfun(value)] = value;
  positionsFilled++;
}

void HTable::remove(int value) //remove a value from the hash table
{
  hashArray[hashfun(value)] = 0;
  positionsFilled--;
}

string HTable::to_string() //output the hash table to the ostream
{
  string toStream;
  for (int i=0; i<150; i++)
  {
    if (hashArray[i]!=0)
    {
      stringstream ss;
      ss << i << " - " << hashArray[i];
      string str = ss.str();
      toStream += str + "\n";
    }
  }
  return toStream;
}

ostream& operator<< (ostream& out, HTable& myTable)
{
  out << endl << myTable.to_string();
  return out;
}

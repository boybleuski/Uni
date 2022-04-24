/*
    HTable.h

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 9/6/2018
    Description: Header file for the HTable class.
*/

#ifndef SAM_HTABLE
#define SAM_HTABLE

#include <cstdlib>
#include <iostream>

using namespace std;

class HTable
{
  public:
  //constructors
  HTable();

  //destructors
  ~HTable();

  //queriers
  int get_position() const;
  //Precondition: None
  //Postcondition: Returns the number of positions filled in the table

  //mutators
  int hashfun(int);
  //Precondition: Receives the value to be inserted into the table
  //Postcondtion: Returns the position to insert the value into
  void add(int);
  //Precondition: Receives the value to be inserted into the table
  //Postcondition: Inserts the value into the table
  void remove(int);
  //Precondition: Receives the value to be removed from the table
  //Postcondition: Removes the value from the table
  string to_string();
  //Precondition: Receives the value to be output
  //Postcondition: Converts the int value to a string, and returns

  private:
    int hashArray[150];
    int positionsFilled;
};

ostream& operator<< (ostream&, HTable&);

#endif

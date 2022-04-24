// Stack.h
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 13/5/2018

#include <cstdlib>
#include <iostream>
#include <string>
#include "LinkedList.h"
#include "Node.h"

using namespace std;

template <class value_type>
class Stack
{
  public:
    //constructor
    Stack();

    //destructor
    ~Stack();

    //queriers
    int size() const;
    // Precondition: None
    // Postcondition: Returns the number of entries in the stack
    bool is_empty() const;
    // Precondition: None
    // Postcondition: Returns true is the stack is empty, false if not
    value_type top();
    // Precondition: None
    // Postcondition: Returns the value of the entry at the top of the stack

    //mutators
    void push(const value_type& obj);
    // Precondition: Receives data to add to the stack
    // Postcondition: Adds a new object to the top of the stack
    value_type pop();
    // Precondition: None
    // Postcondition: Returns the value of the object on top of the stack, and
    // removes the object from the stack

  private:
    LinkedList<value_type> data;
    int used;
};

template <class value_type>
ostream& operator << (ostream&, Stack<value_type>&);
// Precondition: Receives a stack to be output
// Postcondition: Alters the ostream to enable output of stacks

#include "Stack.template"

// Node.h
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 12/5/2018

#ifndef SAM_NODE
#define SAM_NODE
#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

template <class value_type>
class Node
{
   public:

    //constuctor
	  Node(value_type& newData, Node* newPrev=NULL, Node* newNext=NULL);

    //destructor
    ~Node();

    //queriers
	  value_type get_data() const;
		//Precondition: None
		//Postcondition: Returns the data stored in the Node
	  Node* get_prev() const;
		//Precondition: None
		//Postcondition: Returns the previous Node
	  Node* get_next() const;
		//Precondition: None
		//Postcondition: Returns the next Node

    //mutators
	  void set_data(value_type& newData);
		//Precondition: Receives data to be stored in the Node
		//Postcondition: Updates data stored in the Node
	  void set_prev(Node* newPrev);
		//Precondition: Receives a pointer to a Node
		//Postcondition: The received Node becomes the previous Node
	  void set_next(Node* newNext);
		//Precondition: Receives a pointer to a Node
		//Postcondition: The received Node becomes the next Node

   private:
      value_type data;
      Node* prev;
      Node* next;
};
#include "Node.template"
#endif

// LinkedList.h
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 20/4/2018

#ifndef SAM_LINKEDLIST
#define SAM_LINKEDLIST
#include <cstdlib>
#include <iostream>
#include <string>
#include <sstream>
#include "Node.h"

using namespace std;

class LinkedList
{
	public:
		typedef Node::value_type value_type;
		//constructor
		LinkedList(value_type);

		//destructor
		~LinkedList();

		//queriers
		Node* get_head() const;
		//Precondition: None
		//Postcondition: Returns the head Node
		Node* get_tail() const;
		//Precondition: None
		//Postcondition: Returns the tail Node
		int get_length() const;
		//Precondition: None
		//Postcondition: Returns the length of the LinkedList

		//mutators
		void set_head(Node*);
		//Precondition: Receives the Node to become the head Node
		//Postcondition: The received Node becomes the head Node
		void set_tail(Node*);
		//Precondition: Receives the Node to become the tail Node
		//Postcondition: The received Node becomes the tail Node
		void fill_list(value_type);
		//Precondition: Receives a generic type of data
		//Postcondition: The value_type is split into sections and used to fill the
		//							 Nodes of a LinkedList.
		void remove(const Node::value_type&);
		//Precondition: Receives data to be compared to the data in the LinkedList
		//Postcondition: Deletes any Nodes with data matching the input and alters
		//							 the surrounding pointers accordingly
		void remove_from_head();
		//Precondition: Called when a head Node must be deleted
		//Postcondition: The second node becomes the head Node and the original is
		//							 deleted
		void remove_from_tail();
		//Precondition: Called when a tail Node must be deleted
		//Postcondition: The second-last node becomes the tail Node and the original
		//							 is deleted
		void operator += (const LinkedList&);
		//Precondition: Receives a LinkedList to be added to another
		//Postcondition: Concatenates the received LinkedList onto the original

	private:
		Node* current;
		Node* head;
		Node* tail;
		int length;
};

ostream& operator << (ostream&, LinkedList&);
//Precondition: Receives a LinkedList to be output
//Postcondition: Alters the ostream so that the LinkedList will be output as a
//							 sentence made of a list of Nodes

#endif

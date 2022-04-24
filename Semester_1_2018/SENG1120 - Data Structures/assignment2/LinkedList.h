// LinkedList.h
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 12/5/2018

#ifndef SAM_LINKEDLIST
#define SAM_LINKEDLIST
#include <cstdlib>
#include <iostream>
#include <string>
#include <sstream>
#include "Node.h"

using namespace std;

template <class value_type>
class LinkedList
{
	public:
		//constructor
		LinkedList();

		//destructor
		~LinkedList();

		//queriers
		Node<value_type>* get_head() const;
		//Precondition: None
		//Postcondition: Returns the head Node
		Node<value_type>* get_tail() const;
		//Precondition: None
		//Postcondition: Returns the tail Node
		Node<value_type>* get_current() const;
		//Precondition: None
	  //Postcondition: Returns the current Node
		int get_length() const;
		//Precondition: None
		//Postcondition: Returns the length of the LinkedList
		value_type get_current_data() const;
		//Precondition: None
		//Postcondition: Returns the data in the current Node

		//mutators
		void start();
		//Precondition: None
		//Postcondition: The current Node moves to the head Node
		void end();
		//Precondition: None
		//Postcondition: The current Node moves to the tail Node
		void set_head(Node<value_type>*);
		//Precondition: Receives the Node to become the head Node
		//Postcondition: The received Node becomes the head Node
		void set_tail(Node<value_type>*);
		//Precondition: Receives the Node to become the tail Node
		//Postcondition: The received Node becomes the tail Node
		void set_current(Node<value_type>*);
		//Precondition: Receives the Node to become the current Node
		//Postcondition: The received Node becomes the current Node
		void fill_list(value_type);
		//Precondition: Receives a generic type of data
		//Postcondition: The value_type is split into sections and used to fill the
		//							 Nodes of a LinkedList.
		void add_to_head(value_type);
		//Precondition: Receives a generic type of data
		//Postcondition: Adds a filled Node to the head of a LinkedList
		void add_to_tail(value_type);
		//Precondition: Receives a generic type of data
		//Postcondition: Adds a filled Node to the tail of a LinkedList
		void remove(const value_type&);
		//Precondition: Receives data to be compared to the data in the LinkedList
		//Postcondition: Deletes any Nodes with data matching the input and alters
		//							 the surrounding pointers accordingly
		value_type remove_from_head();
		//Precondition: Called when a head Node must be deleted
		//Postcondition: The second node becomes the head Node and the original is
		//							 deleted
		value_type remove_from_tail();
		//Precondition: Called when a tail Node must be deleted
		//Postcondition: The second-last node becomes the tail Node and the original
		//							 is deleted

	private:
		Node<value_type>* current;
		Node<value_type>* head;
		Node<value_type>* tail;
		int length;
};

#include "LinkedList.template"
#endif

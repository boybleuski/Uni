// LinkedList.cpp
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 20/4/2018

#include <cstdlib>
#include <iostream>
#include <string>
#include <sstream>
#include "LinkedList.h"

using namespace std;

//constructor
LinkedList::LinkedList(value_type data)
{
	current = NULL;
	head = NULL;
	tail = NULL;
	length = 0;
	fill_list(data);
}

//destructor
LinkedList::~LinkedList()
{
	current = head;
	do
	{
		remove_from_head();
	}
	while(current!=NULL);
}

//queriers
Node* LinkedList::get_head() const
{
	return head;
}

Node* LinkedList::get_tail() const
{
	return tail;
}

int LinkedList::get_length() const // output the total length in words/Nodes of the list
{
	return length;
}

//mutators
void LinkedList::set_head(Node* newHead)
{
	head = newHead;
}

void LinkedList::set_tail(Node* newTail)
{
	tail = newTail;
}

void LinkedList::fill_list(value_type data) // fill a LinkedList with data-containing Nodes on construction
{
	stringstream startList(data); // sets up data to be manipulated in a stringstream
	value_type fillData;
	Node::value_type* input = new Node::value_type;

  // splits the string into words, inserts the words into individual nodes as
	// data, until the end of the line.
	while (getline(startList, fillData, ' '))
	{
	  input = new Node::value_type;
		*input = fillData;
		length++;

		if (head == NULL) // if the ListedList is empty
		{
			head = new Node(*input); // creates the first Node at the front of the list
			current = head;
			tail = head;
		}

		else
		{
			Node* newNode = new Node(*input); // creates a new Node and inserts data
			tail->set_next(newNode); // defines the latest Node as the end of the list
			newNode->set_prev(current); // defines the Node created before as the previous Node
			tail = newNode;
			current = newNode;
		}
	}
}

void LinkedList::remove(const Node::value_type& data)
{
	for (current=head; current!=NULL; current=current->get_next()) // searches the entire LinkedList for matches.
	{
		if (data == current->get_data()) // checks whether the user-defined data matches what is in a specific Node.
		{
			if (current->get_prev()==NULL) // if the match is the head Node, make the head Node the next Node and delete the original head.
			{
				remove_from_head();
			}

			else if (current->get_next()==NULL) // if the match is the tail Node, make the tail Node the previous Node and delete the original tail.
			{
				remove_from_tail();
			}

			else
			{
				Node* temp;
				// when removing Nodes from the middle of the list, the surrounding Nodes
				// are made to point to each other before the relevent Node is deleted.
				temp = current->get_prev();
				temp->set_next(current->get_next());
				temp = current->get_next();
				temp->set_prev(current->get_prev());
				delete current;
				current = temp;
			}
			length--;
		}
	}
}

void LinkedList::remove_from_head() // designation when the head Node is removed
{
	Node* temp;
	temp = head;
	head = head->get_next();
	current = head;
	if (head!=NULL)
	{
		head->set_prev(NULL);
	}
	else
	{
		tail = NULL;
	}
	delete temp;
}

void LinkedList::remove_from_tail() // designation when the tail Node is removed
{
		Node* temp;
		temp = tail;
		tail = tail->get_prev();
		current = tail;
		if (tail!=NULL)
		{
			tail->set_next(NULL);
		}
		else
		{
			head = NULL;
		}
		delete temp;
}

void LinkedList::operator += (const LinkedList& myList) // enable concentation of LinkedLists.
{
	Node* current = myList.get_head();
	while (current!=NULL)
	{
		fill_list(current->get_data());
		current = current->get_next();
	}
}

ostream& operator << (ostream& out, LinkedList& myList) // enable output of LinkedLists.
{
	Node* current = myList.get_head();
	while (current!=NULL)
	{
		out << current->get_data() << " ";
		current = current->get_next();
	}
	return out;
}

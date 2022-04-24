// Node.cpp
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 20/4/2018

#include <string>
#include <cstdlib>
#include "Node.h"

using namespace std;

Node::Node(value_type& newData, Node* newPrev, Node* newNext)
{
	data = newData;
	prev = newPrev;
	next = newNext;
}

Node::~Node()
{
	data = "";
	prev = NULL;
	next = NULL;
}

string Node::get_data() const
{
	return data;
}

Node* Node::get_prev() const
{
	return prev;
}

Node* Node::get_next() const
{
	return next;
}

void Node::set_data(value_type& newData)
{
	data = newData;
}

void Node::set_prev(Node* newPrev)
{
	prev = newPrev;
}

void Node::set_next(Node* newNext)
{
	next = newNext;
}

#include <string>
#include "Node.h"


using namespace std;

Node::Node(Account newData, Node* newNext, Node* newPrev)
{
	data = newData;
	next = newNext;
	prev = newPrev;
}

Node* Node::get_next()
{
	return next;
}

Node* Node::get_prev()
{
	return prev;
}

Account Node::get_data()
{
	return data;
}

void Node::set_next(Node* newNext)
{
	next = newNext;
}

void Node::set_prev(Node* newPrev)
{
	prev = newPrev;
}

void Node::set_data(Account newData)
{
	data = newData;
}

ostream& operator << (ostream& out, Node* myAcc)
{
	Account myData = myAcc->get_data();
	out << "(" << myData.getName() << ", $" << myData.balance() << ")";
	return out;
}
#include <iostream>
#include <string>
#include <cstdlib>
#include "LinkedList.h"

LinkedList::LinkedList()
{
	head = NULL;
	tail = NULL;
	current = NULL;
	listSize = 0;
}

LinkedList::~LinkedList()
{
	current = head;
	do
	{
		remove_from_head();
	}
	while(current!=NULL);
}

void LinkedList::add_to_head(value_type data)
{
	if (head == NULL)
	{
		head = new Node(data);
		current = head;
		tail = head;
	}

	else
	{
		Node* newNode = new Node(data);
		current = newNode;
		head->set_prev(current);
		current->set_prev(NULL);
		current->set_next(head);
		head = current;
	}
	listSize++;
}

void LinkedList::add_to_tail(value_type data)
{
	if (tail == NULL)
	{
		tail = new Node(data);
		current = tail;
		head = tail;
	}

	else
	{
	  Node* newNode = new Node(data);
	  current = newNode;
	  tail->set_next(current);
	  current->set_next(NULL);
	  current->set_prev(tail);
	  tail = current;
	}
	listSize++;
}

void LinkedList::add_current(value_type data)
{
	Node* newNode = new Node(data);
	Node* temp = newNode;
	temp = current->get_next();
	temp->set_prev(newNode);
	current->set_next(newNode);
	newNode->set_prev(current);
	newNode->set_next(temp);
	current = newNode;
	listSize++;

}

string LinkedList::remove(string deleter)
{
	value_type accData;
	for (current=head; current!=NULL; current=current->get_next())
	{
		Account compareData = current->get_data();
		if (deleter == compareData.getName())
		{
			if (current == head)
				accData = remove_from_head();
			else if (current == tail)
				accData = remove_from_tail();
			else
				accData = remove_from_current();
		}
	}
	return accData;
}

string LinkedList::remove_from_head()
{
	value_type accData;
	Node* temp;
	temp = head;
	head = head->get_next();
	current = head;
	if (head!=NULL)
		head->set_prev(NULL);
	else
	  tail = NULL;
	accData = temp->get_data();
	delete temp;
	listSize--;
	return accData;
}

string LinkedList::remove_from_tail()
{
	value_type accData;
	Node* temp;
	temp = tail;
	tail = tail->get_prev();
	current = tail;
	tail->set_next(NULL);
  accData = temp->get_data();
	delete temp;
	listSize--;
	return accData;
}

string LinkedList::remove_from_current()
{
	value_type accData;
	Node* temp;
	temp = current->get_prev();
	temp->set_next(current->get_next());
	temp = current->get_next();
	temp->set_prev(current->get_prev());
	accData = current->get_data();
	delete current;
	current = temp;
	listSize--;
	return accData;
}

void LinkedList::start()
{
	if (head!=NULL)
	  current = head;
}

void LinkedList::end()
{
	if (tail!=NULL)
	  current = tail;
}

void LinkedList::forward()
{
	if (current->get_next()!=NULL)
	  current = current->get_next();
}

void LinkedList::back()
{
	if (current->get_prev()!=NULL)
	  current = current->get_prev();
}

void LinkedList::print_list()
{
	for (current=head; current!=NULL; current=current->get_next())
	{
		cout << current->get_data() << endl;
	}
}

string LinkedList::get_current()
{
	return current->get_data();
}

int LinkedList::size()
{
	return listSize;
}

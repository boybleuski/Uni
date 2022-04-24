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
		head = new Node<value_type>(data);
		current = head;
		tail = head;
	}

	else
	{
		Node<value_type>* newNode = new Node<value_type>(data);
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
	Node<value_type>* newNode = new Node<value_type>(data);
	current = newNode;
	tail->set_next(current);
	current->set_next(NULL);
	current->set_prev(tail);
	tail = current;
	listSize++;
}

void LinkedList::add_current(value_type data)
{
	Node<value_type>* newNode = new Node<value_type>(data);
	Node<value_type>* temp = newNode;
	temp = current->get_next();
	temp->set_prev(newNode);
	current->set_next(newNode);
	newNode->set_prev(current);
	newNode->set_next(temp);
	current = newNode;
	listSize++;

}

void LinkedList::remove(string deleter)
{
	for (current=head; current!=NULL; current=current->get_next())
	{
		value_type compareData = current->get_data();
		if (deleter == compareData.getName())
		{
			if (current == head)
				remove_from_head();
			else if (current == tail)
				remove_from_tail();
			else
				remove_from_current();
		}
	}
}

void LinkedList::remove_from_head()
{
	Node* temp;
	temp = head;
	head = head->get_next();
	current = head;
	if (head!=NULL)
		head->set_prev(NULL);
	else
	  tail = NULL;
	delete temp;
	listSize--;
}

void LinkedList::remove_from_tail()
{
	Node<value_type>* temp;
	temp = tail;
	tail = tail->get_prev();
	current = tail;
	tail->set_next(NULL);
	delete temp;
	listSize--;
}

void LinkedList::remove_from_current()
{
	Node<value_type>* temp;
	temp = current->get_prev();
	temp->set_next(current->get_next());
	temp = current->get_next();
	temp->set_prev(current->get_prev());
	delete current;
	current = temp;
	listSize--;
}

void LinkedList::start()
{
}

void LinkedList::end()
{
}

void LinkedList::forward()
{
}

void LinkedList::back()
{
}

void LinkedList::print_list()
{
	for (current=head; current!=NULL; current=current->get_next())
	{
		cout << current << endl;
	}
}

Account LinkedList::get_current()
{
	return current->get_data();
}

int LinkedList::size()
{
	return listSize;
}

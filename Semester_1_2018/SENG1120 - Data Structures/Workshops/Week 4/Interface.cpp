#include <iostream>
#include <string>
#include <cstdlib>
#include "Node.h"

using namespace std;

int main()
{
	Node* head = NULL;
	Node* tail = NULL;
	Node* current = NULL;
	int input;
	string deleter;
	
	cout << "How many accounts?" << endl;
    cin >> input;
	for (int i=0; i<input; i++)
	{
		string name;
		double balance;
		cout << "Account " << i+1 << " name: " << endl;
		cin >> name;
		cout << name << "'s starting balance: " << endl;
		cin >> balance;
		cout << endl;
		Account data(name,balance);
		
		if (head == NULL)
		{
			head = new Node(data);
			current = head;
			tail = head;
		}
		else
		{
			Node* newNode = new Node(data);
			tail->set_next(newNode);
			newNode->set_prev(current);
			tail = newNode;
			current = newNode;
		}
	}
	
	for (current=head; current!=NULL; current=current->get_next())
	{
		cout << current << endl;
	}
	
	cout << "Delete which account?" << endl;
	cin >> deleter;
	
	for (current=head; current!=NULL; current=current->get_next())
	{
		Account compareData = current->get_data();
		if (deleter == compareData.getName())
		{
			if (current == head)
			{
				Node* temp;
				temp = head;
				head = head->get_next();
				current = head;
				head->set_prev(NULL);
				delete temp;
			}
			
			else if (current == tail)
			{
				Node* temp;
				temp = tail;
				tail = tail->get_prev();
				current = tail;
				tail->set_next(NULL);
				delete temp;
			}
			
			else
			{
				Node* temp;
				temp = current->get_prev();
				temp->set_next(current->get_next());
				temp = current->get_next();
				temp->set_prev(current->get_prev());
				delete current;
				current = temp;
			}
		}
	}
	
	for (current=head; current!=NULL; current=current->get_next())
		{
			cout << current << endl;
		}
	return 0;
};
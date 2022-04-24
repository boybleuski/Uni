#include <iostream>
#include <string>
#include <cstdlib>
#include "LinkedList.h"
#include "Node.h"

using namespace std;

int main()
{
	LinkedList<Account> myList;
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

		if (myList.size()==0)
			myList.add_to_head(data);
		else
			myList.add_to_tail(data);
	}

	myList.print_list();

	cout << "Delete which account?" << endl;
	cin >> deleter;

	myList.remove(deleter);
	myList.print_list();

	return 0;
}

#include <iostream>
#include <string>
#include <cstdlib>
#include "Queue.h"
#include "LinkedList.h"
#include "Node.h"

using namespace std;

int main()
{
	typedef Queue::value_type value_type;
  LinkedList myList;
	Queue myQueue;
	int input;
	value_type data;

	cout << "How many inputs?" << endl;
  cin >> input;
	for (int i=0; i<input; i++)
	{
		cout << "Input "<< i+1 << ": " << endl;
		cin >> data;
		cout << data << endl;
		myQueue.enqueue(data);
	}
	myList.print_list();

  //if (myQueue.size()!=0)
//	{
  //  cout << "Dequeue once: " << endl;
  //	myQueue.dequeue();
	//	myList.print_list();
//	}

	//if (myQueue.size()!=0)
//	{
	//  cout << "Dequeue again: " << endl;
	  //myQueue.dequeue();
//	  myList.print_list();
//  }

	cout << "Enqueue data: " << endl;
	data = "Fortissimo!";
	//myQueue.enqueue(data);
	myList.print_list();

	return 0;
}

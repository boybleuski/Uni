// This program creates an instance of a simple account object and
// performs some operations on it
// Programmer:  Alex Mendes
// Last modified:  01 Aug 2016
// This file should be used in conjunction with Lab 1 for SENG1120

#include <iostream>
#include <cstdlib>
#include "account.h"

using namespace std;

int main()
{
    cout << "Creating first account with initial amount 10" << endl;

    account my_account1;
    account* ptr = &my_account1;
    ptr->deposit(10);
    account my_copy1 = my_account1;
    account* ptr1 = &my_copy1;

	if (ptr->has_funds())
    {
        cout << "Balance is " << ptr->balance() << endl;
        cout << "Deposit 100" << endl;
        ptr->deposit(100);
        cout << "Balance is " << ptr->balance() << endl;
        cout << "Withdraw 75" << endl;
        ptr->withdraw(75);
        cout << "Balance is " << ptr->balance() << endl;
        cout << "Copy balance is " << ptr1->balance() << endl;
    }
    else
        cout << "Account is empty" << endl;

	cout << "Creating second account with initial amount 0" << endl;
	account my_account2;
	my_account2.deposit(0);
	if (my_account2.has_funds())
	{
		cout << "Balance is " << my_account2.balance() << endl;
		cout << "Deposit 200" << endl;
		my_account2.deposit(200);
		cout << "Balance is " << my_account2.balance() << endl;
		cout << "Withdraw 140" << endl;
		my_account2.withdraw(140);
		cout << "Balance is " << my_account2.balance() << endl;
	}
	else {cout << "Account is empty" << endl;}

	return EXIT_SUCCESS;
}

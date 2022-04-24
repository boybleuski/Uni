// This implements a class that represents an
// account object for double numbers of a currency.
// Programmer: Alex Mendes
// Last modified:  14 Dec 2017
// This file should be used in conjunction with Lab 1 for SENG1120

#include <string>
#include <iostream>
#include <cstdlib>
#include "Account.h"

using namespace std;

Account::Account(const string newName, const double initialValue)
{
    acc_balance = initialValue;
	name = newName;
}

void Account::setName(string newName)
{
	name = newName;
}

string Account::getName() const
{
	return name;
}

void Account::deposit(double amount)
{
    acc_balance += amount;
}

void Account::withdraw(double amount)
{
    acc_balance -= amount;
}

double Account::balance() const
{
    return acc_balance;
}

bool Account::has_funds() const
{
	if (acc_balance > 0.0) {return true;} 
	else {return false;}
}
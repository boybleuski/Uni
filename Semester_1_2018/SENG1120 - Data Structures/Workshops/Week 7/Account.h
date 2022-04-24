// This defines a class that represents an
// account object for double numbers of a currency.
// Programmer: Alex Mendes
// Last modified:  14 Dec 2017
// This file should be used in conjunction with Lab 1 for SENG1120
#ifndef SAM_ACCOUNT
#define SAM_ACCOUNT
#include <string>
#include <iostream>

using namespace std;

class Account
{
public:
    // Members that are externally visible

    // These are member functions

    // Constructor
    // Precondition: none
    // Postcondition: A new instance of account is created
    // and its instance data initialsed to either zero or a
    // parameter-provided value
    Account(const string name = "Not given", const double initialValue = 0.0);

    // Members that mutate data

    void setName(const string);
    void deposit(const double amount);
	void withdraw(const double amount);

    // Members that query data

    string getName() const;
    double balance() const;
    bool has_funds() const;

private:
    double acc_balance;
	string name;
};

#endif

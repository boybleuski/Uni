#include <iostream>
#include <cstdlib>
#include "account.h"

using namespace std;

int main()
{
    cout << "Creating first account with initial amount 10" << endl;

    account my_account1;
    my_account1.deposit(10);
    int* ptr1 = my_account1;
}

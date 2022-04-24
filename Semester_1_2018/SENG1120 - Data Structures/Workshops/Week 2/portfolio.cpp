/*
  This implements a portfolio class for storing an individual's instances
  of accounts
  Author: Sam Dolbel
  Last modified: 13/6/2018
  To be used in conjunction with Lab 2
*/

#include "portfolio.h"

portfolio::portfolio()
{
  
}

portfolio::portfolio(account acc1, account acc2)
{
  savings.deposit(acc1.balance());
  cheque.deposit(acc2.balance());
}

double portfolio::savings_bal()
{
  return savings.balance();
}

double portfolio::cheque_bal()
{
  return cheque.balance();
}

void portfolio::savings_zero()
{
  savings.withdraw(savings.balance());
}

void portfolio::cheque_zero()
{
  cheque.withdraw(cheque.balance());
}

void portfolio::savings_withdraw(double amount)
{
  savings.withdraw(amount);
}

void portfolio::cheque_withdraw(double amount)
{
  cheque.withdraw(amount);
}

void portfolio::savings_deposit(double amount)
{
  savings.deposit(amount);
}

void portfolio::cheque_deposit(double amount)
{
  cheque.deposit(amount);
}

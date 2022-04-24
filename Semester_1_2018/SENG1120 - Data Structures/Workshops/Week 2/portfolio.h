/*
  This class defines a portfolio for storing a number of different Account
  types
  Author: Sam Dolbel
  Date modified: 13/6/2018
  To be used in conjunction with Lab 2
*/

#ifndef SAM_PORTFOLIO
#define SAM_PORTFOLIO

#include "account.h"

class portfolio
{
  public:
    //constructor
    portfolio();
    portfolio(account, account);

    //queriers
    double savings_bal();
    double cheque_bal();

    //mutators
    void savings_zero();
    void cheque_zero();
    void savings_withdraw(double);
    void cheque_withdraw(double);
    void savings_deposit(double);
    void cheque_deposit(double);

  private:
    account savings;
    account cheque;
};

#endif

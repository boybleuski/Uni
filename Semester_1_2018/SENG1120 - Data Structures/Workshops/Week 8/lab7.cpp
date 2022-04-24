// Author: Sam Dolbel
// Date modified: 1/5/2018
// Program: Lab 7

#include <iostream>
#include <cstdlib>
#include <string>
#include <sstream>

using namespace std;

int fibo(int n)
{
	if (n == 1 || n == 2)
		return 1;
	else
		return (fibo(n-1) + fibo(n-2));
}

int factorial(int n)
{
	if (n == 1 || n == 0)
	  return 1;
	else
	  return n*factorial(n-1);
}

string goodGrammar(int input)
{
	if (input % 10 == 1 && input % 100 != 11)
		return "st";
	else if (input % 10 == 2 && input % 100 != 12)
		return "nd";
	else if (input % 10 == 3 && input % 100 != 13)
		return "rd";
	else
		return "th";
}

string combine(string input1, string input2)
{
	return "Nope.";
}

int main()
{
	int input;
	string input2;

	cout << "1 = Fibonacci" << endl << "2 = Combine" << endl << "3 = Factorial" << endl;
	cin >> input;

	if (input == 1)
	{
	  cout << "Enter an integer (preferably less than 35):" << endl;
	  cin >> input;
    int result = fibo(input);
    cout << "The " << input << goodGrammar(input) << " position is " << result << ".";
	}
	else if (input == 2)
	{
		cout << "Input a string:" << endl;
		cin >> input2;
		cout << sizeof(input2) << endl;
		cout << combine("", input2) << endl;
	}
	else if (input == 3)
	{
		cout << "Input an integer." << endl;;
		cin >> input;
		cout << factorial(input) << endl;
	}
	else
		cout << "Nup, ya blew it." << endl;

    return 0;
  }

#include <iostream>
#include <cstdlib>
#include "Item.h"

using namespace std;

int compare(int obj1, int &obj2)
{
  if (obj1 < obj2)
    return -1;
  else if (obj1 == obj2)
    return 0;
  else
    return 1;
}

int find(int x[], int arrayFirstItem, int arrayLastItem, int toFind)
{
  if (arrayFirstItem > arrayLastItem)
    return -1;
  else
  {
    int midpoint = (arrayFirstItem+arrayLastItem)/2;
    if (compare(toFind, x[midpoint]) == 0)
      return midpoint;
    else if (compare(toFind, x[midpoint]) == -1)
      return (find(x, arrayFirstItem, midpoint-1, toFind));
    else
      return (find(x, midpoint+1, arrayLastItem, toFind));
  }
}

int main()
{
  int x[100];
  int myChoice;
  for (int i=0; i<100; i++)
    x[i] = i*2;
  cout << "Enter an even integer: (0-198)" << endl;
  cin >> myChoice;
  myChoice = find(x, 0, 199, myChoice);

  if (myChoice == -1)
    cout << "Your integer was not in the array." << endl;
  else
    cout << "Your integer was in position " << find(x, 0, 199, myChoice) << "." << endl;

  for(int i=0; i<100; i++)
    {
      cout << x[i] << ", " ;
    }

  return 0;
}

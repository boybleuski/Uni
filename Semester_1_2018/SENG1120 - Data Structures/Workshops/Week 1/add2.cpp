#include <iostream>

using namespace std;

  int main()
  {
    int celsius=0;
    cout << "Enter an integer value." << endl;
    cin >> celsius;
    cout << "Celsius = " << celsius << endl;
    cout << "Fahrenheit = " << 9*celsius/5+32 << endl;
    return 0;
  }

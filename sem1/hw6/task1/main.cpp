#include <iostream>
#include "convertToExponential.h"

using namespace std;

int main()
{
    double number = 0.0;
    cout << "Enter a number: ";
    cin >> number;
    cout << endl;
    convertToExponential(number);
    return 0;
}

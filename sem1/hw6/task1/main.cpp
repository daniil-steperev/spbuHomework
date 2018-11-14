#include <iostream>
#include "printExponential.h"

using namespace std;

int main()
{
    double number = 0.0;
    cout << "Enter a number: ";
    cin >> number;
    cout << endl;
    printExponential(number);
    return 0;
}

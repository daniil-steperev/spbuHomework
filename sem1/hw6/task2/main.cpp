#include <iostream>
#include "operations.h"

using namespace std;

int main()
{
    int firstNumber = 0;
    int secondNumber = 0;
    cout << "Enter two numbers: ";
    cin >> firstNumber >> secondNumber;
    binaryOperations(firstNumber, secondNumber);
    return 0;
}

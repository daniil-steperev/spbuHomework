#include <iostream>
#include "binary.h"
#include "stack.h"

using namespace std;

void binaryOperations(int firstNumber, int secondNumber)
{
    Stack *firstBinary = createStack();
    Stack *secondBinary = createStack();
    translateToBinary(firstBinary, firstNumber);
    translateToBinary(secondBinary, secondNumber);

    printingInBinary(firstBinary, secondBinary);

    summingBinary(firstBinary, secondBinary);
    summing(firstNumber, secondNumber);

    deleteStack(firstBinary);
    deleteStack(secondBinary);
}

int main()
{
    int firstNumber = 0;
    int secondNumber = 0;
    cout << "Enter two numbers: ";
    cin >> firstNumber >> secondNumber;
    binaryOperations(firstNumber, secondNumber);
    return 0;
}

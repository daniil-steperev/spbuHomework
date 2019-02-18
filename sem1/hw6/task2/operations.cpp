#include <iostream>
#include "binary.h"
#include "operations.h"

const int maxSize = 50;

void binaryOperations(int firstNumber, int secondNumber)
{
    int *firstBinary = new int[maxSize] {0};
    int *secondBinary = new int[maxSize] {0};
    translateToBinary(firstBinary, firstNumber);
    translateToBinary(secondBinary, secondNumber);

    printInBinary(firstBinary, secondBinary);

    sumBinary(firstBinary, secondBinary);
    sum(firstNumber, secondNumber);

    delete[] firstBinary;
    delete[] secondBinary;
}

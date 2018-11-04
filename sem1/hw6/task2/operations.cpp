#include <iostream>
#include "stack.h"
#include "binary.h"
#include "operations.h"

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

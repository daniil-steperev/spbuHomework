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

    printingInBinary(firstBinary, secondBinary);

<<<<<<< HEAD
    sumBinary(firstBinary, secondBinary);
=======
    summingBinary(firstBinary, secondBinary);
>>>>>>> d72617ab2b1da9426affa18044d627aa891e07a7
    sum(firstNumber, secondNumber);

    delete[] firstBinary;
    delete[] secondBinary;
}

#include <iostream>
#include "binary.h"

using namespace std;
const int maxSize = 50;

void sum(int firstNumber, int secondNumber)
{
    cout << "Sum in decimal: ";
    cout << firstNumber + secondNumber;
}

void printSummary(int *sumOfBinary, int size)
{
    cout << "Sum in binary: ";
    int i = 0;
    while (sumOfBinary[i] != 1)
    {
        i++;
    }
    while (i < size)
    {
        cout << sumOfBinary[i];
        i++;
    }
    cout << "\n";
}

int maxOfTwo(int first, int second)
{
    if (first > second)
    {
        return first;
    }
    return second;
}

void sumBinary(int *firstBinary, int *secondBinary)
{
    bool isNegative = false;
    if (firstBinary[0] == 1 || secondBinary[0] == 1)
    {
        isNegative = true;
    }

    int *sumOfBinary = new int[maxSize] {0};

    int degree = 0; // that's for case when '1 + 1 = 2'
    for (int i = maxSize - 1; i >= 0; i--)
    {
        int first = firstBinary[i];
        int second = secondBinary[i];
        int newNumber = first + second + degree;
        if (newNumber <= 1)
        {
            sumOfBinary[i] = newNumber;
            degree = 0;
        }
        else
        {
            degree = 1;
            sumOfBinary[i] = newNumber % 2;
        }
    }

    if (!isNegative && degree == 0)
    {
        sumOfBinary[0] = 0;
    }

    if (degree == 1 && isNegative)
    {
        int i = maxSize;
        degree = sumOfBinary[i] + 1;
        if (degree == 1)
        {
            sumOfBinary[i] = 1;
        }
        else
        {
            sumOfBinary[i] = 0;
            i--;
            while (degree != 0)
            {
                if (sumOfBinary[i] == 0)
                {
                    sumOfBinary[i] = 1;
                    degree = 0;
                }
                else
                {
                    sumOfBinary[i] = 0;
                    i--;
                }
            }
        }
    }

    printSummary(sumOfBinary, maxSize);

    delete[] sumOfBinary;
}

void printInBinary(int *firstBinary, int *secondBinary)
{
    cout << "First number in binary code: ";
    print(firstBinary);
    cout << "\n";
    cout << "Second number in binary code: ";
    print(secondBinary);
    cout << "\n" << endl;
}

int abs(int number)
{
    if (number < 0)
        return -number;
    return number;
}

void translateNegative(int *binaryNumber, int number)
{
    number = abs(number);
    int index = maxSize - 1;
    while (number > 0)
    {
        if (number % 2 == 0)
        {
            binaryNumber[index] = 1; // invert bit value to get a number representation in reverse code
            index--;
        }
        else
        {
            binaryNumber[index] = 0; // invert bit value to get a number representation in reverse code
            index--;
        }
        number /= 2;
    }
    while (index >= 0)
    {
        binaryNumber[index] = 1;
        index--;
    }
}

void translateToBinary(int *binaryNumber, int number)
{
    if (number < 0)
    {
        translateNegative(binaryNumber, number);
        return;
    }

    int index = maxSize - 1;
    while (number > 0)
    {
        if (number % 2 == 0)
        {
            binaryNumber[index] = 0;
            index--;
        }
        else
        {
            binaryNumber[index] = 1;
            index--;
        }
        number /= 2;
    }
}

void print(int *binaryNumber)
{
    int i = 0;
    while (binaryNumber[i] != 1)
    {
        i++;
    }
    for (i; i < maxSize; i++)
    {
        cout << binaryNumber[i];
    }
}

#include <iostream>
#include "binary.h"
#include "stack.h"

using namespace std;
const int maxSize = 50;

void summing(int firstNumber, int secondNumber)
{
    cout << "Sum in decimal: ";
    cout << firstNumber + secondNumber;
}

void printingSummary(int *sumOfBinary, int size)
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

void reverseStack(Stack *oldStack, Stack *reversedStack)
{
    while (!isEmpty(oldStack))
    {
        int element = pop(oldStack);
        push(reversedStack, element);
    }
}

void summingBinary(Stack *firstBinary, Stack *secondBinary)
{
    int sizeOfFirst = sizeOfStack(firstBinary);
    int sizeOfSecond = sizeOfStack(secondBinary);
    int size = maxOfTwo(sizeOfFirst, sizeOfSecond) + 1;
    bool isNegative = false;
    if (size == maxSize + 1);
    {
        isNegative = true;
    }

    int *sumOfBinary = new int[size] {0};

    Stack *reversedFirst = createStack();
    reverseStack(firstBinary, reversedFirst);

    Stack *reversedSecond = createStack();
    reverseStack(secondBinary, reversedSecond);


    int degree = 0; // that's for case when '1 + 1 = 2'
    for (int i = size - 1; i > 0; i--)
    {
        int first = pop(reversedFirst);
        int second = pop(reversedSecond);
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

    if (degree && isNegative)
    {
        int i = maxSize + 1;
        degree = (sumOfBinary[i] + 1) % 2;
        if (degree == 0)
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

    printingSummary(sumOfBinary, size);

    delete[] sumOfBinary;
    deleteStack(reversedFirst);
    deleteStack(reversedSecond);
}

void printingInBinary(Stack *firstBinary, Stack *secondBinary)
{
    cout << "First number in binary code: ";
    printStack(firstBinary);
    cout << "\n";
    cout << "Second number in binary code: ";
    printStack(secondBinary);
    cout << "\n" << endl;
}

int abs(int number)
{
    if (number < 0)
        return -number;
    return number;
}

void translateNegative(Stack *stack, int number)
{
    number = abs(number);
    while (number > 0)
    {
        if (number % 2 == 0)
        {
            push(stack, 1); // invert bit value to get a number representation in reverse code
        }
        else
        {
            push(stack, 0); // invert bit value to get a number representation in reverse code
        }
        number /= 2;
    }
    while (sizeOfStack(stack) != maxSize) // invert bit value to get a number representation in reverse code
    {
        push(stack, 1);
    }
}

void translateToBinary(Stack *stack, int number)
{
    if (number < 0)
    {
        translateNegative(stack, number);
        return;
    }

    while (number > 0)
    {
        if (number % 2 == 0)
        {
            push(stack, 0);
        }
        else
        {
            push(stack, 1);
        }
        number /= 2;
    }
}

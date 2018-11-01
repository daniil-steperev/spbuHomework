#include <iostream>
#include "binary.h"
#include "stack.h"

using namespace std;

void summing(int firstNumber, int secondNumber)
{
    cout << "Sum in decimal: ";
    cout << firstNumber + secondNumber;
}

void printingSummary(int *sumOfBinary, int size)
{
    cout << "Sum of binary: ";
    if (sumOfBinary[0] == 0)
    {
        for (int i = 1; i < size; i++)
        {
            cout << sumOfBinary[i];
        }
        cout << "\n";
        return;
    }
    for (int i = 0; i < size; i++)
        {
            cout << sumOfBinary[i];
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
    int *sumOfBinary = new int[size] {0};

    Stack *reversedFirst = createStack();
    reverseStack(firstBinary, reversedFirst);

    Stack *reversedSecond = createStack();
    reverseStack(secondBinary, reversedSecond);


    int degree = 0; // that's for case when '1 + 1 = 2'
    for (int i = size - 1; i >= 0; i--)
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
    if (degree == 1)
    {
        sumOfBinary[0] = 1;
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

void translateToBinary(Stack *stack, int number)
{
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

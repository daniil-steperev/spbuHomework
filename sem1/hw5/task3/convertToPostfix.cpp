#include <iostream>
#include <limits.h>
#include "stack.h"
#include "convertToPostfix.h"

using namespace std;

const int size = 256;

int freeSpace(char *string)
{
    for (int i = 0; i < size; i++)
    {
        if (string[i] == '\0')
            return i;
    }
}

void convertingFromStack(Stack *stack, char *postfixString)
{
    int element = 0;
    while (element != INT_MIN)
    {
        element = pop(stack);
        postfixString[freeSpace(postfixString)] = element;
    }
}

void convertingFromBrackets(Stack *stack, char *postfixString)
{
    while (true)
    {
        int element = pop(stack);
        if (element == '(')
                return;
        postfixString[freeSpace(postfixString)] = element;
    }
}

bool isOperation(char operation)
{
    return ((operation == '(') || (operation == '+') || (operation == '-') || (operation == '*') || (operation == '/'));
}

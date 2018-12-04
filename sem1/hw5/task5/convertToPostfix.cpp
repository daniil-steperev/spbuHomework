#include <iostream>
#include <limits.h>
#include "stack.h"
#include "convertToPostfix.h"
#include "countExpression.h"
using namespace std;

const int length = 256;

void convertToPostfix(char *infixString)
{
    Stack *stack = createStack();
    char postfixString[length] = {'\0'};
    for (int i = 0; i < length; i++)
    {
        if (infixString[i] == '\0')
            break;
        if (infixString[i] <= '9' && infixString[i] >= '0')
        {
            postfixString[returnFreeSpace(postfixString)] = infixString[i];
        }
        else if (isOperation(infixString[i]))
        {
            push(stack, infixString[i]);
        }
        else if (infixString[i] == ')')
        {
            convertFromBrackets(stack, postfixString);
        }
    }
    convertFromStack(stack, postfixString);

    countExpression(postfixString);

    deleteStack(stack);
}

int returnFreeSpace(char *string)
{
    for (int i = 0; i < length; i++)
    {
        if (string[i] == '\0')
        {
            return i;
        }
    }
}
 void convertFromStack(Stack *stack, char *postfixString)
{
    int element = 0;
    while (element != INT_MIN)
    {
        element = pop(stack);
        postfixString[returnFreeSpace(postfixString)] = element;
    }
}

void convertFromBrackets(Stack *stack, char *postfixString)
{
    while (true)
    {
        int element = pop(stack);
        if (element == '(')
        {
            return;
        }
        postfixString[returnFreeSpace(postfixString)] = element;
    }
}

bool isOperation(char operation)
{
    return ((operation == '(') || (operation == '+') || (operation == '-') || (operation == '*') || (operation == '/'));
}

#include <iostream>
#include <limits.h>
#include "stack.h"
#include "convertToPostfix.h"
using namespace std;
const int length = 256;

void convertingToPostfix(char *infixString)
{
    Stack *stack = createStack();
    char postfixString[length] = {'\0'};
    for (int i = 0; i < length; i++)
    {
        if (infixString[i] == '\0')
            break;
        if (infixString[i] <= '9' && infixString[i] >= '0')
        {
            postfixString[freeSpace(postfixString)] = infixString[i];
        }
        else if (isOperation(infixString[i]))
        {
            push(stack, infixString[i]);
        }
        else if (infixString[i] == ')')
        {
            convertingFromBrackets(stack, postfixString);
        }
    }
    convertingFromStack(stack, postfixString);
    cout << "Postfix string: " << postfixString << endl;
    deleteStack(stack);
}

int freeSpace(char *string)
{
    for (int i = 0; i < length; i++)
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

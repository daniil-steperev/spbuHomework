#include <iostream>
#include "stack.h"
#include <limits.h>

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

void convertingToPostfix(char *infixString)
{
    Stack *stack = createStack();
    char postfixString[size] = {'\0'};
    for (int i = 0; i < size; i++)
    {
        if (infixString[i] == '\0')
            break;
        if (infixString[i] <= '9' && infixString[i] >= '0')
        {
            postfixString[freeSpace(postfixString)] = infixString[i];
        }
        else if (infixString[i] == '(' || infixString[i] == '+' || infixString[i] == '-' || infixString[i] == '*' || infixString[i] == '/')
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

int main()
{
    char infixString[size] = {'\0'};
    cout << "Enter the expression in infix variation: ";
    cin >> infixString;
    convertingToPostfix(infixString);
    return 0;
}

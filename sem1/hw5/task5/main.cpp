#include <iostream>
#include <cctype>
#include <cstdlib>
#include <limits.h>
#include "stack.h"

using namespace std;

const int size = 256;

int countingIntermediateResult(Stack * stack, char operation)
{
    int firstNumber = pop(stack);
    int secondNumber = pop(stack);
    if (secondNumber == INT_MIN)
    {
        return INT_MIN;
    }
    if (operation == '+')
    {
        return firstNumber + secondNumber;
    }
    else if (operation == '-')
    {
        return secondNumber - firstNumber;
    }
    else if (operation == '*')
    {
        return firstNumber * secondNumber;
    }
    else if (operation == '/')
    {
        return secondNumber / firstNumber;
    }

}

void countingExpression(char *expression)
{
    Stack *stack = createStack();
    for (int i = 0; i < size; i++)
    {
        if (expression[i] == '\0')
            break;
        if (isdigit(expression[i]))
        {
            push(stack, expression[i] - '0');
        }
        else
        {
            int intermediate = countingIntermediateResult(stack, expression[i]);
            if (intermediate == INT_MIN)
            {
                cout << "ERROR!";
                return;
            }
            push(stack, intermediate);
        }
    }
    int result = pop(stack);
    cout << "The result is: " << result;
    deleteStack(stack);
}

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

char *convertingToPostfix()
{
    Stack *stackInf = createStack();
    cout << "Enter string in infix variation: ";
    char infixString[256] = {'\0'};
    cin >> infixString;
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
            push(stackInf, infixString[i]);
        }
        else if (infixString[i] == ')')
        {
            convertingFromBrackets(stackInf, postfixString);
        }
    }
    convertingFromStack(stackInf, postfixString);
    deleteStack(stackInf);
    countingExpression(postfixString);
}

int main()
{
    convertingToPostfix();
    return 0;
}

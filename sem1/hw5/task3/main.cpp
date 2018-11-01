#include <iostream>
#include <limits.h>
#include "stack.h"
#include "convertToPostfix.h"

using namespace std;
const int size = 256;


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

int main()
{
    char infixString[size] = {'\0'};
    cout << "Enter the expression in infix variation: ";
    cin >> infixString;

    convertingToPostfix(infixString);
    return 0;
}

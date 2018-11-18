#include <iostream>
#include "countingExpression.h"

using namespace std;

const int length = 256;

<<<<<<< HEAD:sem1/hw5/task5.2/countingExpression.cpp
int countingIntermediateResult(Stack *stack, char operation)
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
    for (int i = 0; i < length; i++)
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
=======
int main()
{
    char expression[length] = {};
    cout << "Enter postfix entry: ";
    cin >> expression;
    countingExpression(expression);
    return 0;
>>>>>>> 8161bf98d6059dbbf3f145e798d2e7adad2b0f4a:sem1/hw5/task4/main.cpp
}

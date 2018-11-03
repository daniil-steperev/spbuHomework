#include <iostream>
#include "countingExpression.h"

using namespace std;

const int length = 256;

int main()
{
    char expression[length] = {};
    cout << "Enter postfix entry: ";
    cin >> expression;
    countingExpression(expression);
    return 0;
}

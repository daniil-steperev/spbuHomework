#include <iostream>
#include "convertToPostfix.h"

using namespace std;

const int length = 256;

int main()
{
    char infixString[length] = {'\0'};
    cout << "Enter the expression in infix variation: ";
    cin >> infixString;

    convertToPostfix(infixString);
    return 0;
}

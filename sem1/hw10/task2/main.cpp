#include <iostream>
#include "myString.h"
#include "rabinKarp.h"

using namespace std;

const int stringSize = 100000;

int main()
{
    cout << "Enter a string: ";
    char *textString = new char[stringSize] {0};
    cin >> textString;

    cout << "Enter a desired string: ";
    char *desiredString = new char[stringSize] {0};
    cin >> desiredString;

    MyString *text = createString(textString);
    MyString *desired = createString(desiredString);

    cout << "Indexes of matches: ";
    algorithmRabinKarp(text, desired);

    delete[] text;
    delete[] desired;
    deleteString(text);
    deleteString(desired);
    return 0;
}

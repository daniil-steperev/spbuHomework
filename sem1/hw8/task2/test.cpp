#include <iostream>
#include "myString.h"
using namespace std;

void printString(MyString *string)
{
    char *outputString = returnChar(string);
    cout << outputString;
    delete[] outputString;
}

void test()
{
    cout << "TEST: " << endl;
    MyString *string = createString("It is a test of the task");
    cout << "The string: '";
    printString(string);
    cout << "' - was created!" << endl;
    cout << endl;

    cout << "Cloned string: '"; // cloned string
    MyString *cloneString = createString();
    cloneString = clone(string);
    printString(string);
    cout << "'"<< endl;
    deleteString(cloneString);
    cout << endl;


    MyString *concString = createString(" I believe that everything is OK"); // concatenated string
    string = concatenate(string, concString);
    cout << "Concatenated string: '";
    printString(string);
    cout << "'"<< endl;
    deleteString(concString);
    cout << endl;

    MyString *firstStr = createString("I get enough sleep"); // equal and not equal string
    MyString *secondStr = createString("To study at Spbu");
    MyString *thirdStr = createString("To study at Spbu");
    if (!isEqual(firstStr, secondStr))
    {
        cout << "'";
        printString(firstStr);
        cout << "'" << " is not equal " << "'";
        printString(secondStr);
        cout << "'" << endl;
    }
    if (isEqual(secondStr, thirdStr))
    {
        cout << "'";
        printString(secondStr);
        cout << "'" << " is equal " << "'";
        printString(thirdStr);
        cout << "'" << endl;
    }
    deleteString(firstStr);
    deleteString(secondStr);
    deleteString(thirdStr);
    cout << endl;

    cout << "Length of '";
    printString(string);
    cout << "' is: " << countLength(string) << endl; // counting length of the string
    cout << endl;
    MyString *subString = createString();
    subString = pickOutSubStr(string, 8, 7);
    cout << "Sub string from the ninth index (length = 7): '";
    printString(subString);
    cout << "'" << endl;
    deleteString(subString);
    deleteString(string);
}

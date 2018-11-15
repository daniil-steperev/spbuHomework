#include <iostream>
#include "myString.h"

using namespace std;

void test()
{
    cout << "TEST: " << endl;
    MyString *string = createString("It is a test of the task");
    cout << "The string: '" << returnChar(string) << "' - was created!" << endl;
    cout << endl;

    cout << "Cloned string: '"; // cloned string
    MyString *cloneString = clone(string);
    cout << returnChar(string)  << "'"<< endl;
    deleteString(cloneString);
    cout << endl;


    MyString *concString = createString(" I believe that everything is OK"); // concatenated string
    string = concatenate(string, concString);
    cout << "Concatenated string: '" << returnChar(string)  << "'"<< endl;
    deleteString(concString);
    cout << endl;

    MyString *firstStr = createString("I get enough sleep"); // equal and not equal string
    MyString *secondStr = createString("To study at Spbu");
    MyString *thirdStr = createString("To study at Spbu");
    if (!are_Equal(firstStr, secondStr))
    {
        cout << "'" <<  returnChar(firstStr) << "'" << " is not equal " << "'" << returnChar(secondStr)  << "'" << endl;
    }
    if (are_Equal(secondStr, thirdStr))
    {
        cout << "'" << returnChar(secondStr) << "'" << " is equal " << "'" << returnChar(thirdStr)  << "'" << endl;
    }
    deleteString(firstStr);
    deleteString(secondStr);
    deleteString(thirdStr);
    cout << endl;

    cout << "Length of '" << returnChar(string) << "' is: " << countLength(string) << endl; // counting length of the string
    cout << endl;
    MyString *subString = pickOutSubStr(string, 8, 7);
    cout << "Sub string from the ninth index (length = 7): '" << returnChar(subString) << "'" << endl;
    deleteString(subString);
    deleteString(string);
}

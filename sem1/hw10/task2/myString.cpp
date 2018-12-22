#include <iostream>
#include <string.h>
#include "myString.h"

using namespace std;

MyString *createString()
{
    return new MyString;
}

int lineLength(char *line)
{
    int count = 0;
    while(line[count] != '\0')
    {
        count++;
    }
    return count;
}

MyString *createString(char *string)
{
    if (string == nullptr)
    {
        return nullptr;
    }

    MyString *newString = new MyString;

    newString->size = lineLength(string);
    newString->content = new char[newString->size];
    for (int i = 0; i < newString->size; i++)
    {
        newString->content[i] = string[i];
    }

    return newString;
}

void deleteString(MyString *string)
{
    delete[] string->content;
    delete string;
}

MyString *clone(MyString *string)
{
    if (string == nullptr || string->content == nullptr)
    {
        return nullptr;
    }
    MyString *newString = createString(string->content);
    return newString;
}

MyString *concatenate(MyString *stringFirst, MyString *stringSecond)
{
    if (isEmpty(stringFirst) && isEmpty(stringSecond))
    {
        return nullptr;
    }
    if (isEmpty(stringFirst))
    {
        return clone(stringSecond);
    }
    if (isEmpty(stringSecond))
    {
        return clone(stringFirst);
    }
    char *newContent = new char[countLength(stringFirst) + countLength(stringSecond) + 1];

    strcpy(newContent, stringFirst->content);
    strcpy(newContent + countLength(stringFirst), stringSecond->content);

    MyString *newString = createString(newContent);

    delete[] newContent;
    return newString;
}

bool isEqual(MyString *stringFirst, MyString *stringSecond)
{
    for (int i = 0; i < stringFirst->size; i++)
    {
        if (stringFirst->content[i] != stringSecond->content[i])
        {
            return false;
        }
    }
    return true;
}

int countLength(MyString *string)
{
    if (string != nullptr)
    {
        return string->size;
    }
    return 0;
}

bool isEmpty(MyString *string)
{
    return (countLength(string) == 0);
}

MyString *subString(MyString *string, int begin, int end)
{
    char *stringChar = returnChar(string);
    char *subStringChar = new char[end - begin + 2];
    for (int i = 0; i <= end - begin; i++)
    {
        subStringChar[i] = stringChar[i + begin];
    }
    subStringChar[end - begin + 1] = '\0';

    MyString *newString = createString(subStringChar);

    delete[] stringChar;
    delete[] subStringChar;

    return newString;
}

char *returnChar(MyString *string)
{
    int size = string->size + 1;
    char *newContent = new char[size];

    for (int i = 0; i < size - 1; i++)
    {
        newContent[i] = string->content[i];
    }

    newContent[size - 1] = '\0';

    return newContent;
 }

#include <iostream>
#include <string.h>
#include "myString.h"

using namespace std;

MyString *createString()
{
    return new MyString;
}

MyString *createString(const char *string)
{
    if (string == nullptr)
    {
        return nullptr;
    }

    MyString *newString = new MyString;

    newString->size = strlen(string);
    newString->content = new char[newString->size + 1];
    strcpy(newString->content, string);

    return newString;
}

void deleteString(MyString *string)
{
    if (string == nullptr)
    {
        return;
    }
    if (string->content != nullptr)
    {
        delete[] string->content;
        string->content = nullptr;
    }
    string->size = 0;
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
    if (isEmpty(stringFirst) && isEmpty(stringSecond))
    {
        return true;
    }

    return (strcmp(stringFirst->content, stringSecond->content) == 0);
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

MyString *pickOutSubStr(MyString *string, const int index, const int length)
{
    if (string == nullptr)
    {
        return nullptr;
    }
    if (isEmpty(string) || index < 0 || index + length >= countLength(string))
    {
        return nullptr;
    }

    char *newContent = new char[length + 1];
    memcpy(newContent, string->content + index, length);
    newContent[length] = '\0'; // last char

    MyString *newString = createString(newContent);
    delete[] newContent;

    return newString;
}

char *returnChar(MyString *string)
{
    if (string == nullptr || isEmpty(string))
    {
        return nullptr;
    }
    char *newContent = new char[countLength(string) + 1];
    strcpy(newContent, string->content);

    return newContent;
 }


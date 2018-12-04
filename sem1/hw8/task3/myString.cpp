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

MyString *concatenate(MyString *firstString, MyString *secondString)
{
    if (isEmpty(firstString) && isEmpty(secondString))
    {
        return nullptr;
    }
    if (isEmpty(firstString))
    {
        return clone(secondString);
    }
    if (isEmpty(secondString))
    {
        return clone(firstString);
    }
    char *newContent = new char[countLength(firstString) + countLength(secondString) + 1];

    strcpy(newContent, firstString->content);
    strcpy(newContent + countLength(firstString), secondString->content);

    MyString *newString = createString(newContent);

    delete[] newContent;
    return newString;
}

bool areEqual(MyString *firstString, MyString *secondString)
{
    if (isEmpty(firstString) && isEmpty(secondString))
    {
        return true;
    }

    return (strcmp(firstString->content, secondString->content) == 0);
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


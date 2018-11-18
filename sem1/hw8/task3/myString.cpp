#include <iostream>
#include <string.h>
#include "myString.h"

using namespace std;

MyString *createString()
{
    return new MyString;
}

MyString *createString(const char *str)
{
    if (str == nullptr)
    {
        return nullptr;
    }

    MyString *newString = new MyString;

    newString->size = strlen(str);
    newString->content = new char[newString->size + 1];
    strcpy(newString->content, str);

    return newString;
}

void deleteString(MyString *str)
{
    if (str == nullptr)
    {
        return;
    }
    if (str->content != nullptr)
    {
        delete[] str->content;
        str->content = nullptr;
    }

    str->size = 0;
}

MyString *clone(MyString *str)
{
    if (str == nullptr || str->content == nullptr)
    {
        return nullptr;
    }
    MyString *newString = createString(str->content);
    return newString;
}

MyString *concatenate(MyString *str1, MyString *str2)
{
    if (is_Empty(str1) && is_Empty(str2))
    {
        return nullptr;
    }
    if (is_Empty(str1))
    {
        return clone(str2);
    }
    if (is_Empty(str2))
    {
        return clone(str1);
    }
    char *newContent = new char[countLength(str1) + countLength(str2) + 1];

    strcpy(newContent, str1->content);
    strcpy(newContent + countLength(str1), str2->content);

    MyString *newString = createString(newContent);

    delete[] newContent;
    return newString;
}

bool are_Equal(MyString *str1, MyString *str2)
{
    if (is_Empty(str1) && is_Empty(str2))
    {
        return true;
    }

    return (strcmp(str1->content, str2->content) == 0);
}

int countLength(MyString *str)
{
    if (str != nullptr)
    {
        return str->size;
    }
    return 0;
}

bool is_Empty(MyString *str)
{
    return (countLength(str) == 0);
}

MyString *pickOutSubStr(MyString *str, const int index, const int length)
{
    if (str == nullptr)
    {
        return nullptr;
    }
    if (is_Empty(str) || index < 0 || index + length >= countLength(str))
    {
        return nullptr;
    }

    char *newContent = new char[length + 1];
    memcpy(newContent, str->content + index, length);
    newContent[length] = '\0'; // last char

    MyString *newString = createString(newContent);
    delete[] newContent;

    return newString;
}

char *returnChar(MyString *str)
{
    if (str == nullptr || is_Empty(str))
    {
        return nullptr;
    }
    char *newContent = new char[countLength(str) + 1];
    strcpy(newContent, str->content);

    return newContent;

}


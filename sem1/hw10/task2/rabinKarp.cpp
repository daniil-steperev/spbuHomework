#include <iostream>
#include "myString.h"
#include "rabinKarp.h"

const int factor = 7;
const int divider = 10243;

int getHash(MyString *str, int start, int end)
{
    int hash = 0;
    char *stringValue = returnChar(str);
    for (int i = start; i < end; i++)
    {
        hash = (hash * factor + stringValue[i]);
    }
    hash %= divider;
    delete[] stringValue;
    return hash;
}

int pow(int number, int degree)
{
    if (degree == 0)
    {
        return 1;
    }
    else if (degree == 1)
    {
        return number;
    }
    else
    {
        if (degree % 2 == 0)
        {
            return pow(number * number, degree / 2);
        }
        else
        {
            return number * pow(number, degree - 1);
        }
    }
}

int changeHash(int hash, int oldCharacter, int newCharacter, int degree)
{
    int redundancy = (oldCharacter * pow(factor, degree)) % divider;
    hash -= redundancy;
    hash = (hash * factor + newCharacter) % divider;
    return hash;
}

void algorithmRabinKarp(MyString *text, MyString *desired)
{
    int textLength = countLength(text);
    int desiredLength = countLength(desired);
    if (desiredLength > textLength)
    {
        return;
    }
    if (desiredLength == textLength)
    {
        if (isEqual(text, desired))
        {
            cout << 1;
        }
        return;
    }

    int desiredHash = getHash(desired, 0, countLength(desired));
    int currentHash = getHash(text, 0, countLength(desired));

    int start = countLength(desired);
    int end = countLength(text);

    char *textString = returnChar(text);
    for (int i = start; i < end; i++)
    {
        if (currentHash == desiredHash)
        {
            MyString *subString = pickOutSubStr(text, i - countLength(desired), countLength(desired));
            if (isEqual(subString, desired))
            {
                cout << i - countLength(desired) + 1 << ' ';
            }
            deleteString(subString);
        }
        int degree = countLength(desired) - 1;
        int oldCharacterIndex = i - countLength(desired);
        currentHash = changeHash(currentHash, textString[oldCharacterIndex], textString[i], degree);
    }
    if (currentHash == desiredHash)
    {
        cout << end - start + 1;
    }
    delete[] textString;
}

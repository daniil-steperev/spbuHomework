#include <iostream>
#include "myString.h"
#include "rabinKarp.h"

const int factor = 13;
const int divider = 1009;

int getHash(MyString *str, int start, int end)
{
    int hash = 0;
    char *stringValue = returnChar(str);
    for (int i = start; i < end; i++)
    {
        hash = ((hash * factor) % divider + (int)stringValue[i]) % divider;
    }
    delete[] stringValue;
    return hash;
}

int changeHash(int hash, char oldCharacter, char newCharacter, int power)
{
    hash = (((((hash - ((int) oldCharacter) * power) % divider + divider) % divider) * factor) % divider
                      + newCharacter) % divider;
	return hash;
}

void algorithmRabinKarp(MyString *text, MyString *desired)
{
    int textLength = countLength(text);
    int desiredLength = countLength(desired);

    int power = 1;
    for (int i = 1; i < desiredLength; i++)
    {
        power = (power * factor) % divider;
    }

    if (desiredLength > textLength)
    {
        return;
    }

    int desiredHash = getHash(desired, 0, countLength(desired));
    int currentHash = getHash(text, 0, countLength(desired));

    int start = 0;
    int end = textLength - desiredLength;

    char *textString = returnChar(text);

    for (int i = start; i <= end; i++)
    {
        if (currentHash == desiredHash)
        {
            MyString *subStr = subString(text, i, i + desiredLength - 1);
            if (isEqual(subStr, desired))
            {
                cout << i + 1 << ' ';
            }
            deleteString(subStr);
        }

        currentHash = changeHash(currentHash, textString[i], textString[i + countLength(desired)], power);
    }
    delete[] textString;
}

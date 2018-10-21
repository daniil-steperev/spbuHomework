#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

const int size = 26;

bool checkingIn(char *string, char element)
{
    for (int i = 0; i < size - 1; i++)
    {
        if (element == string[i])
            return 0;
    }
    return 1;
}

int emptySpace(char *array)
{
    for (int i = 0; i < size - 1; i++)
    {
        if (array[i] == '\0')
            return i;
    }
}

void leaveOneLetter(char *string)
{
    char arrayLetters[size] = {'\0'};
    char newString[size] = {'\0'};
    for (int i = 0; i < strlen(string); i++)
    {
        if (checkingIn(arrayLetters, string[i]))
        {
            newString[emptySpace(newString)] = string[i];
            arrayLetters[emptySpace(arrayLetters)] = string[i];
        }
    }
    cout << newString << endl;
    delete[] newString;
    delete[] arrayLetters;
}

int main()
{
    char buff[50] = {};
    ifstream fileIn("file.txt");
    if (!fileIn.is_open())
    {
        cout << "File can not be opened!";
    }
    else
    {
        cout << "Words compiled by first occurrence of each letter: " << endl;
        while (!fileIn.eof())
        {
            fileIn.getline(buff, 50);
            leaveOneLetter(buff);
        }
    }
    fileIn.close();
    return 0;
}

#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

const int sizeOfString = 256;
const int sizeOfArray = 26; // there are only 26 lowercase letters of the latin alphabet

void printAnswer(int *arrayOfFrequency)
{
    cout << "The number of occurrences of each letter in the file:\n";
    for (int i = 0; i < sizeOfArray; i++)
    {
        if (arrayOfFrequency[i] != 0)
        {
            char letter = i + 'a';
            cout << letter << ": " << arrayOfFrequency[i] << "\n";
        }
    }
}

void countLetter(int *arrayOfFrequency, char *string)
{
    int lengthOfString = strlen(string);
    for (int i = 0; i < lengthOfString; i++)
    {
        int index = string[i] - 'a';
        if ((0 <= index)  && (index <= 25))
        {
            arrayOfFrequency[index]++;
        }
    }
}

int main()
{
    char *string = new char[sizeOfString] {0}; // the line that was received from file is stored here
    int *arrayOfFrequency = new int[sizeOfArray] {0}; // the number of counted letters is stored here
    ifstream fileIn("file.txt");
    if (!fileIn.is_open())
    {
        cout << "File can not be opened!";
    }
    else
    {
        while (!fileIn.eof())
        {
            fileIn.getline(string, sizeOfString);
            countLetter(arrayOfFrequency, string);
        }
    }
    printAnswer(arrayOfFrequency);
    fileIn.close();
    delete[] string;
    delete[] arrayOfFrequency;
    return 0;
}

#include <iostream>
#include <fstream>
#include <string.h>
#include "list.h"

using namespace std;
const int buffSize = 1000;

bool isCorrectChar(char element, char nextElement) // element is not "null", "*", brackets and space (as we consider it separately)
{
    return (element != '*' && element != ' ' && element != '(' && element != ')' && nextElement != 'u');
}

bool isEnd(int i, int length)
{
    return (i > length);
}

void changeString(string &lastString, char bracket, char lastBracket)
{
    if (bracket == '(' && lastBracket == '(') // we should add "0" in this case
    {
        lastString += "0";
    }
    else if (bracket == '(' && lastBracket == ')') // we should add "1" in this case
    {
        lastString += "1";
    }
    else if (bracket == ')') // we should delete last symbol in this case
    {
        lastString.erase(lastString.size() - 1);
    }
}

void readCharactersCodes(char *buff, List *characters)
{
    string code = {};
    string lastString = {};

    int lastStringLength = 1;
    int length = strlen(buff);
    int i = 3;

    char symbol = '\0';
    char lastBracket = '(';
    while (i < length) // start from "3" as we should not consider first bracket
    {
        if (buff[i] == 'n' && buff[i + 1] == 'u')
        {
            i += 4;
        }
        else if (buff[i] == ' ')
        {
            i++;
        }
        else if (buff[i] == '(')
        {
            changeString(lastString, '(', lastBracket);
            i++;
            lastBracket = '(';
        }
        else if (buff[i] == ')')
        {
            if (lastString.size() == 0) // end of work
            {
                break;
            }
            changeString(lastString, ')', lastBracket);
            i++;
            lastBracket = ')';
        }
        else if (isCorrectChar(buff[i], buff[i + 1]))
        {
            code = lastString;

            if (buff[i] == '\'' && buff[i + 2] == '\'') // case when symbol is space
            {
                addToList(characters, ' ', code);
                i += 3;
            }
            else
            {
                addToList(characters, buff[i], code);
                i++;
            }
        }
        else if (buff[i] == '*')
        {
            i++;
        }
    }
}

void translateMessage(ofstream &file, List *characters, char *message)
{
    string word = {};
    int length = strlen(message);
    int i = 0;
    while (i < length)
    {
        while (i < length && message[i] != ' ')
        {
            word += message[i];
            i++;
         }
        writeElementInChar(file, characters, word);
        word = {};
        i++;
    }
}

void mainEncoding(ifstream &file, ofstream &fileOutput)
{
    char *tree = new char[buffSize] {0};
    char *message = new char[buffSize] {0};
    List *characters = createList();
    file.getline(tree, buffSize);
    file.getline(message, buffSize);

    readCharactersCodes(tree, characters);

    translateMessage(fileOutput, characters, message);

    delete[] message;
    delete[] tree;
    deleteList(characters);
}

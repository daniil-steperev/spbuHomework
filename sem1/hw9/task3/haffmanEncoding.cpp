#include <iostream>
#include <fstream>
#include <string.h>
#include "binaryTree.h"

using namespace std;
const int buffSize = 1000;

void encodeMessage(ifstream &file, ofstream &fileOut)
{
    BinaryTree *huffmanTree = createBinaryTree();

<<<<<<< HEAD
    getTree(huffmanTree, file);
=======
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
>>>>>>> 9b024774a64533f4130170003d6b335c0bcd6d9b

    file.get(); // get '\n'
    char *coddedMessage = new char[buffSize] {0};
    file.getline(coddedMessage, buffSize);
    int length = strlen(coddedMessage);

    translateMessage(huffmanTree, coddedMessage, length, fileOut);

    deleteTree(huffmanTree);
    delete[] coddedMessage;
}

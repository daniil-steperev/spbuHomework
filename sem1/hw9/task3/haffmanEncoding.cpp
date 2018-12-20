#include <iostream>
#include <fstream>
#include <string.h>
#include "binaryTree.h"

using namespace std;
const int buffSize = 1000;

void encodeMessage(ifstream &file, ofstream &fileOut)
{
    BinaryTree *huffmanTree = createBinaryTree();

    getTree(huffmanTree, file);

    file.get(); // get '\n'
    char *coddedMessage = new char[buffSize] {0};
    file.getline(coddedMessage, buffSize);
    int length = strlen(coddedMessage);

    translateMessage(huffmanTree, coddedMessage, length, fileOut);

    deleteTree(huffmanTree);
    delete[] coddedMessage;
}

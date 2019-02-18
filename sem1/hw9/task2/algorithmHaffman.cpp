#include <iostream>
#include <string.h>
#include "queue.h"
#include "operations.h"
#include "binaryTree.h"
#include "algorithmHaffman.h"
#include "list.h"

using namespace std;

const int buffSize = 1000;

void haffmanAlgorithm(ifstream &fin, ofstream &file, PriorityQueue *queue, int *alphabet)
{
    file << "Table of frequency: " << endl;
    printTableOfFrequency(file, alphabet);
    file << endl;

    file << "Binary trees: " << endl;
    int counter = 1;
    while (sizeOfQueue(queue) > 1)
    {
        file << counter << ": ";
        counter++;
        BinaryTree *first = pop(queue);
        BinaryTree *second = pop(queue);
        BinaryTree *newTree = mergeTrees(first, second);
        writeTree(file, newTree);
        file << endl;
        push(queue, newTree);
    }

    file << "Codes of elements " << endl;
    List *codeList = createList();
    BinaryTree *tree = pop(queue);
    assignCodes(file, tree, codeList);
    file << endl;

    //addElementsToList(codeList, tree);

    fin.clear();
    fin.seekg(0, ios::beg);

    char symbol = '\0';

    file << "Coded message: ";
    char *buff = new char [buffSize] {0};
    while (!fin.eof())
    {
        fin.getline(buff, buffSize);
        int length = strlen(buff);
        for (int i = 0; i < length; i++)
        {
            symbol = buff[i];
            writeElementInCode(file, codeList, symbol);
        }
    }

    deleteList(codeList);
    deleteTree(tree);
    delete[] buff;
}

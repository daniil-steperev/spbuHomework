#include <iostream>
#include <string.h>
#include "queue.h"
#include "operations.h"
#include "binaryTree.h"
#include "algorithmHaffman.h"
#include "list.h"
using namespace std;


void haffmanAlgorithm(ofstream &file, PriorityQueue *queue, int *alphabet, char *buff)
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
    BinaryTree *tree = pop(queue);
    assignCodes(file, tree);
    file << endl;

    List *codeList = createList();
    addElementsToList(codeList, tree);

    file << "Coded message: ";
    int length = strlen(buff);
    char symbol = '\0';
    for (int i = 0; i < length; i++)
    {
        symbol = buff[i];
        writeElementInCode(file, codeList, symbol);
    }

    deleteList(codeList);
    deleteTree(tree);
}

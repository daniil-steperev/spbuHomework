#include <fstream>
#include <iostream>
#include <string.h>
#include "binaryTree.h"
#include "queue.h"
#include "algorithmHaffman.h"
using namespace std;

const int alphabetLength = 256;
const int maxStringSize = 1000;

void getElements(char *line, int *alphabet)
{
    int length = strlen(line);
    for (int i = 0; i < length; i++)
    {
        int index = line[i];
        alphabet[index]++;
    }
}

void addElementsToQueue(int *alphabet, PriorityQueue *queue)
{
    for (int i = 0; i < alphabetLength; i++)
    {
        if (alphabet[i] > 0)
        {
            BinaryTree *tree = createBinaryTree(alphabet[i]);
            addToTree(tree, i);
            push(queue, tree);
        }
    }
}

void printTableOfFrequency(ofstream &file, int *alphabet)
{
    for (int i = 0; i < alphabetLength; i++) // print table of frequency of elements
    {
        if (alphabet[i] > 0)
        {
            if ((char)i == ' ')
            {
                file << "' ' - " << alphabet[i] << endl;
            }
            else
            {
                file << (char)i << " - " << alphabet[i] << endl;
            }
        }
    }
}


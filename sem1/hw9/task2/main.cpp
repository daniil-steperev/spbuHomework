#include <iostream>
#include <fstream>
#include "queue.h"
#include "operations.h"
#include "algorithmHaffman.h"
#include <vld.h>
using namespace std;

const int fileNameSize = 1000;
const int alphabetLength = 256;
const int maxStringSize = 1000;

int main()
{
    cout << "Input file name: ";
    char *fileName = new char [fileNameSize] {0};
    cin >> fileName;
    ifstream file(fileName);
    delete[] fileName;

    if (!file.is_open())
    {
        cout << "File can not be opened!";
        return 0;
    }
    char *buff = new char[maxStringSize] {0};
    file.getline(buff, maxStringSize);

    int *alphabet = new int[alphabetLength] {0};
    PriorityQueue *haffmanQueue = createPriorityQueue();

    getElements(buff, alphabet);
    addElementsToQueue(alphabet, haffmanQueue);

    ofstream fileOutput("fileOutput.txt");
    haffmanAlgorithm(fileOutput, haffmanQueue, alphabet, buff);

    delete[] alphabet;
    delete[] buff;
    deletePriorityQueue(haffmanQueue);
    fileOutput.close();
    file.close();
    return 0;
}

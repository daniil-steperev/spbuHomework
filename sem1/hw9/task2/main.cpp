#include <iostream>
#include <fstream>
#include "queue.h"
#include "operations.h"
#include "algorithmHaffman.h"

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
    int *alphabet = new int[alphabetLength] {0};
    PriorityQueue *haffmanQueue = createPriorityQueue();

    while (!file.eof())
    {
        file.getline(buff, maxStringSize);
        getElements(buff, alphabet);
        addElementsToQueue(alphabet, haffmanQueue);
    }

    ofstream fileOutput("fileOutput.txt");
    haffmanAlgorithm(file, fileOutput, haffmanQueue, alphabet);

    delete[] alphabet;
    delete[] buff;
    deletePriorityQueue(haffmanQueue);
    fileOutput.close();
    file.close();
    return 0;
}

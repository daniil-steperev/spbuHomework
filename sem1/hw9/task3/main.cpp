#include <fstream>
#include <iostream>
#include "haffmanEncoding.h"
using namespace std;

const int fileNameSize = 1000;

int main()
{
    cout << "Input file name: ";
    char *fileName = new char [fileNameSize] {0};
    cin >> fileName;
    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File can not be opened!";
        return 0;
    }

    ofstream fileOutput("fileOutput.txt");
    mainEncoding(file, fileOutput);

    fileOutput.close();
    file.close();
    delete[] fileName;
    return 0;
}

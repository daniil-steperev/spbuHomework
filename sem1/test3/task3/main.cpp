#include <iostream>
#include <fstream>
#include "operations.h"

using namespace std;

const int fileNameSize = 1000;

int main()
{
    cout << "Enter file name: ";
    char *fileName = new char [fileNameSize] {0};
    cin >> fileName;

    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File cannot be opened!";
        delete[] fileName;
        return 0;
    }

    int numberOfElements = 0;
    int numberOfWays = 0;
    file >> numberOfElements >> numberOfWays;

    int **matrix = createMatrix(file, numberOfElements, numberOfWays);

    cout << "Vertexes: ";
    findVertexes(matrix, numberOfElements, numberOfWays);

    deleteMatrix(matrix, numberOfElements, numberOfWays);
    delete[] fileName;
    file.close();
    return 0;
}

#include <iostream>
#include <fstream>
#include "findSaddlePoint.h"

using namespace std;

int main()
{
    ifstream file("input.txt");
    if (!file.is_open())
    {
        cout << "File cannot be opened!";
        return 0;
    }

    int numberOfStrings = 0;
    int numberOfColumns = 0;
    file >> numberOfStrings >> numberOfColumns;

    int **matrix = new int*[numberOfStrings];
    fillMatrix(file, matrix, numberOfStrings, numberOfColumns);
    printSaddlePoints(matrix, numberOfStrings, numberOfColumns);

    file.close();
    deleteMatrix(matrix, numberOfStrings);
    return 0;
}

#include <iostream>
#include <fstream>
#include "findSaddlePoint.h"

using namespace std;

int returnMinInString(int *stringOfNumbers, int numberOfColumns)
{
    int minElement = stringOfNumbers[0];
    for (int i = 0; i < numberOfColumns; i++)
    {
        if (stringOfNumbers[i] < minElement)
        {
            minElement = stringOfNumbers[i];
        }
    }
    return minElement;
}

int* findMaxInColumn(int **matrix, int numberOfStrings, int indexOfColumns)
{
    int maxElem = matrix[0][indexOfColumns];
    int *maxString = matrix[0];
    for (int i = 0; i < numberOfStrings; i++)
    {
        if (matrix[i][indexOfColumns] > maxElem)
        {
            maxElem = matrix[i][indexOfColumns];
            maxString = matrix[i];
        }
    }
    return maxString;
}

void deleteMatrix(int **&matrix, int numberOfStrings)
{
    for (int i = 0; i < numberOfStrings; i++)
    {
        delete[] matrix[i];
    }
    delete matrix;
}

void fillMatrix(ifstream &file, int **matrix, int numberOfStrings, int numberOfColumns)
{
    for (int i = 0; i < numberOfStrings; i++)
    {
        matrix[i] = new int[numberOfColumns] {0};
        for (int j = 0; j < numberOfColumns; j++)
        {
            file >> matrix[i][j];
        }
    }
}

void printSaddlePoints(int **matrix, int numberOfStrings, int numberOfColumns)
{
    cout << "Saddle points of matrix: ";
    for (int i = 0; i < numberOfStrings; i++)
    {
        int *maxInColumn = findMaxInColumn(matrix, numberOfStrings, i);
        int minInString = returnMinInString(maxInColumn, numberOfColumns);
        for (int j = 0; j < numberOfColumns; j++)
        {
            if (matrix[i][j] == minInString)
            {
                cout << matrix[i][j] << ' ';
            }
        }
        delete[] maxInColumn;
    }
}

#include <iostream>
#include "operations.h"

void deleteMatrix(int** matrix, int numberOfElements, int numberOfWays)
{
    for (int i = 0; i < numberOfElements; i++)
    {
        delete[] matrix[i];
    }

    delete[] matrix;
}

int **createMatrix(ifstream &file, int numberOfElements, int numberOfWays)
{
    int **matrix = new int *[numberOfElements];
    for (int i = 0; i < numberOfElements; i++)
    {
        matrix[i] = new int [numberOfWays];
        for (int j = 0; j < numberOfWays; j++)
        {
            file >> matrix[i][j];
        }
    }

    return matrix;
}

void resetValues(bool *used, int numberOfElements)
{
    for (int i = 0; i < numberOfElements; i++)
    {
        used[i] = false;
    }
}

int findNewVertex(int **matrix, int numberOfElements, int currentVertex)
{
    for (int i = 0; i < numberOfElements; i++)
    {
        if (matrix[i][currentVertex] == 1)
        {
            return i;
        }
    }

    return 0; // there are not any outgoing vertexes
}

void findAnotherWays(int **matrix, bool *used, int numberOfElements, int index, int numberOfWays, int currentVertex)
{
    for (int i = 0; i < numberOfWays; i++)
    {
        if (matrix[index][i] == -1)
        {
            int anotherVertex = findNewVertex(matrix, numberOfElements, i);
            if (!used[anotherVertex])
            {
                used[anotherVertex] = true;
                findAnotherWays(matrix, used, numberOfElements, anotherVertex, numberOfWays, currentVertex);
            }
        }
    }
}

bool suitableVertex(bool *used, int numberOfElements)
{
    for (int i = 0; i < numberOfElements; i++)
    {
        if (!used[i])
        {
            return false;
        }
    }

    return true;
}

void findVertexes(int **matrix, int numberOfElements, int numberOfWays)
{
    bool *used = new bool [numberOfElements];

    for (int i = 0; i < numberOfElements; i++)
    {
        resetValues(used, numberOfElements);

        findAnotherWays(matrix, used, numberOfElements, i, numberOfWays, i);

        used[i] = true;
        if (suitableVertex(used, numberOfElements))
        {
            cout << i << ' ';
        }
    }

    delete[] used;
}

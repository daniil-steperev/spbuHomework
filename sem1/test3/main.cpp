#include <iostream>
#include <fstream>
#include <limits>
#include <algorithm>
#include <iomanip>
using namespace std;

const int infinity = 10000000;

void findTops(int **matrix, int size)
{
    int count = 0;
    for (int i = 0; i < size; i++)
    {
        count = 0;
        for (int j = 0; j < size; j++)
        {
            count++;
            if (i != j && matrix[i][j] == infinity)
            {
                count = 0;
                break;
            }
        }
        if (count == size - 1)
        {
            cout << i << ' ';
        }
    }
}

void algorithmFloydWarshall(int **matrix, int size)
{
    for (int k = 0; k < size; k++) // cycle for tops of matrix
    {
        for (int i = 0; i < size; i++) // position i
        {
            for (int j = 0; j < size; j++) // position j
            {
                if (matrix[i][j] < infinity && matrix[k][j] < infinity) // check whether there is a way from i to j, from j to j
                {
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j] || matrix[i][j] == 0)
                    {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
    }
}


int **fillGraph(ifstream &file, int size)
{
    int **matrix = new int *[size];
    for (int i = 0; i < size; i++)
    {
        matrix[i] = new int[size] {0};
        for (int j = 0; j < size; j++)
        {
            int number = 0;
            file >> number;
            if (number == 0 && i != j)
            {
                matrix[i][j] = infinity;
            }
            else if (i == j)
            {
                matrix[i][j] = 0;
            }
            else
            {
                matrix[i][j] = number;
            }
        }
    }
    return matrix;
}

void deleteMatrix(int **matrix, int size)
{
    for (int i = 0; i < size; i++)
    {
        delete[] matrix[i];
    }
    delete matrix;
}

int main()
{
    ifstream file("input.txt");
    if (!file.is_open())
    {
        cout << "File cannot be opened!";
        return 0;
    }

    int size = 0;
    file >> size;
    int **matrix = fillGraph(file, size);
    algorithmFloydWarshall(matrix, size);
    findTops(matrix, size);
    printMatrix(matrix, size);

    deleteMatrix(matrix);
    file.close();
    return 0;
}

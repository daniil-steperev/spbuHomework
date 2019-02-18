#include <iostream>
#include "graph.h"
#include "aStar.h"
using namespace std;

bool **createBoolMatrix(int size)
{
    bool **matrix = new bool* [size];
    for (int i = 0; i < size; i++)
    {
        matrix[i] = new bool [size] {false};
        for (int j = 0; j < size; j++)
        {
            matrix[i][j] = false;
        }
    }
    return matrix;
}

int **createIntMatrix(int size)
{
    int **matrix = new int* [size];
    for (int i = 0; i < size; i++)
    {
        matrix[i] = new int [size];
        for (int j = 0; j < size; j++)
        {
            matrix[i][j] = -1;
        }
    }
    return matrix;
}

void deleteBoolMatrix(int size, bool **matrix)
{
    for (int i = 0; i < size; i++)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}

void deleteIntMatrix(int size, int **matrix)
{
    for (int i = 0; i < size; i++)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}

bool isInsideTheBorder(int size, int x, int y) // checks whether a point is inside the border
{
    return (x < size && y < size && x >= 0 && y >= 0);
}

void checkAdjacent(int **matrix, int size, int **distances, bool **used, int **previousHeightParametres, int **previousWidthParametres, int x, int y, int newX, int newY) // checks adjacent point
{
    if (isInsideTheBorder(size, newX, newY) && matrix[newY][newX] != 1 && (distances[newY][newX] == -1 || distances[newY][newX] > distances[y][x] + 1))
    {
        distances[newY][newX] = distances[y][x] + 1; // since all the ways in our graph->matrix costs "1"
        previousHeightParametres[newY][newX] = y;
        previousWidthParametres[newY][newX] = x;
    }
}

bool isCorrectPoint(int **matrix, int size, int **distances, int newX, int newY, bool **used, int minF, int y, int x, Node &finish)
{
    return (isInsideTheBorder(size, newX, newY) && matrix[newY][newX] != 1 && !used[newY][newX] && (minF == -1 ||
            minF > distances[y][x] + 1 + heuristicFunction(newX, newY, finish.x, finish.y)));
}

void checkAdjacentPoint(int **matrix, int size, int **distances, bool **used, Node &finish, Node &current, int &minF, int y, int x, int newY, int newX)
{
    if (isCorrectPoint(matrix, size, distances, newX, newY, used, minF, y, x, finish))
    {
        minF = distances[y][x] + 1 + heuristicFunction(newY, newX, finish.x, finish.y);
        current.x = newX;
        current.y = newY;
    }
}

void changeDistances(int **matrix, int size, int **distances, bool **used, int **previousHeightParametres, int **previousWidthParametres, Node &point)
{
    checkAdjacent(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, point.x, point.y, point.x + 1, point.y);
    checkAdjacent(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, point.x, point.y, point.x - 1, point.y);
    checkAdjacent(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, point.x, point.y, point.x, point.y + 1);
    checkAdjacent(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, point.x, point.y, point.x, point.y - 1);
}

void changeValue(int **matrix, int size, int **distances, bool **used, Node &finish, Node &current, int &minF, int y, int x)
{
    checkAdjacentPoint(matrix, size, distances, used, finish, current, minF, y, x, y + 1, x);
    checkAdjacentPoint(matrix, size, distances, used, finish, current, minF, y, x, y - 1, x);
    checkAdjacentPoint(matrix, size, distances, used, finish, current, minF, y, x, y, x + 1);
    checkAdjacentPoint(matrix, size, distances, used, finish, current, minF, y, x, y, x - 1);
}

void printAnswerMatrix(int **matrix, int size, Node &start, Node &finish)
{
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if (matrix[i][j] == '*')
            {
                cout << "* ";
            }
            else if (i == start.y && j == start.x)
            {
                cout << "S ";
            }
            else if (i == finish.y && j == finish.x)
            {
                cout << "F ";
            }
            else
            {
                cout << matrix[i][j] << ' ';
            }
        }
        cout << endl;
    }
}

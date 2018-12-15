#include <iostream>
#include <cmath>
#include "graph.h"
#include "aStar.h"
#include "matrixFunctions.h"

using namespace std;

int heuristicFunction(int currentX, int currentY, int finishX, int finishY)
{
    int squaredX = (currentX - finishX) * (currentX - finishX);
    int squaredY = (currentY - finishY) * (currentY - finishY);
    int allowableDistance = sqrt(squaredX + squaredY);
    return allowableDistance;
}

void printInt(bool **used, int n)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << used[i][j] << ' ';
        }
        cout << endl;
    }
}

void algorithmAStar(int **matrix, int size, int startX, int startY, int finishX, int finishY)
{
    bool **used = createBoolMatrix(size); // already seen tops

    int **previousHeightParametres = createIntMatrix(size); // this would be useful to pave the way between START and FINISH
    int **previousWidthParametres = createIntMatrix(size); // this would be useful to pave the way between START and FINISH

    int **distances = createIntMatrix(size); // distances between tops (in our case it reflects the number of tops needed to achieve FINISH

    Node start;
    start.x = startX;
    start.y = startY;

    Node finish;
    finish.x = finishX;
    finish.y = finishY;

    used[start.y][start.x] = true;
    distances[start.y][start.x] = 0;
    changeDistances(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, start);

    Node current;
    bool isWayExists = false;
    while (true)
    {
        int minF = -1;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (used[i][j])
                {
                    changeValue(matrix, size, distances, used, finish, current, minF, i, j);
                }
            }
        }

        if (current.x == finish.x && current.y == finish.y)
        {
            isWayExists = true;
            break;
        }

        if (minF == -1)
        {
            break;
        }

        used[current.y][current.x] = true;
        changeDistances(matrix, size, distances, used, previousHeightParametres, previousWidthParametres, current);
    }

    if (!isWayExists)
    {
        cout << "There is no way between point START and point FINISH!";
    }
    else
    {
        cout << "Distance from point START (" << startY + 1 << ", " << startX + 1 << ')';
        cout << " to point FINISH (" << finishY + 1 << ", " << finishX + 1 << "): " << distances[finish.y][finish.x] << endl;

        int temporaryX = previousWidthParametres[finish.y][finish.x];
        int temporaryY = previousHeightParametres[finish.y][finish.x];
        int tmp = 0;
        while (temporaryY != start.y || temporaryX != start.x)
        {
            matrix[temporaryY][temporaryX] = '*';
            tmp = temporaryX;

            temporaryX = previousWidthParametres[temporaryY][temporaryX];
            temporaryY = previousHeightParametres[temporaryY][tmp];
        }

        cout << "Way from point START to point FINISH (marked with '*'): " << endl;
        printAnswerMatrix(matrix, size, start, finish);

    }
    deleteBoolMatrix(size, used);
    deleteIntMatrix(size, previousWidthParametres);
    deleteIntMatrix(size, previousHeightParametres);
    deleteIntMatrix(size, distances);
}

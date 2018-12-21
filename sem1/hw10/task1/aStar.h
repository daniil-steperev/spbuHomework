#pragma once
#include "graph.h"

struct Node
{
    int x = 0;
    int y = 0;
};

void algorithmAStar(int **matrix, int size, int startX, int startY, int finishX, int finishY);
int heuristicFunction(int startX, int startY, int finishX, int finishY);

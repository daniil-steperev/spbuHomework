#pragma once
#include <fstream>
using namespace std;

struct Graph
{
    int size;
    int **matrix;
};

Graph *createGraph(ifstream &file);
void printMatrix(Graph *graph);

void deleteGraph(Graph *graph);


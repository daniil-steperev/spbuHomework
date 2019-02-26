#pragma once
#include <fstream>
using namespace std;

struct Graph
{
    int size; // as we have a square matrix, we need only one parameter
    int **matrix;
};

Graph *createGraph(int size);
void deleteGraph(Graph *graph);

void getGraph(Graph *graph, int numberOfRoads, ifstream &file);
void printGraph(Graph *graph);

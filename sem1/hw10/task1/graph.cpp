#include <iostream>
#include <fstream>
#include "graph.h"

using namespace std;

Graph *createGraph(ifstream &file)
{
    int size = 0;
    file >> size;

    Graph *graph = new Graph;
    int **matrix = new int* [size];
    for (int i = 0; i < size; i++)
    {
        matrix[i] = new int [size] {0};
        for (int j = 0; j < size; j++)
        {
            file >> matrix[i][j];
        }
    }
    graph->matrix = matrix;

    graph->size = size;
    return graph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->size; i++)
    {
        delete[] graph->matrix[i];
    }
    delete[] graph->matrix;
    delete graph;
}

void printMatrix(Graph *graph)
{
    for (int i = 0; i < graph->size; i++)
    {
        for (int j = 0; j < graph->size; j++)
        {
            cout << graph->matrix[i][j] << ' ';
        }
        cout << endl;
    }
}

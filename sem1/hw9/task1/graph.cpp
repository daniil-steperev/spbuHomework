#include <iostream>
#include <fstream>
#include <limits.h>
#include "graph.h"
using namespace std;
const int infinity = INT_MAX;

Graph *createGraph(int size)
{
    Graph *graph = new Graph;
    graph->size = size;

    int **matrix = new int *[size];
    for (int i = 0; i < size; i++)
    {
        matrix[i] = new int[size] {0};
    }
    graph->matrix = matrix;
	return graph;
}

void getGraph(Graph *graph, int numberOfRoads, ifstream &file)
{
    for (int i = 0; i < numberOfRoads; i++)
    {
        int firstTown = 0;
        int secondTown = 0;
        int lengthOfRoad = 0;
        file >> firstTown >> secondTown >> lengthOfRoad;
        firstTown--; // because we start numerating from "0"
        secondTown--;
        graph->matrix[firstTown][secondTown] = lengthOfRoad;
        graph->matrix[secondTown][firstTown] = lengthOfRoad;
    }
}

void printGraph(Graph *graph)
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

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->size; i++)
    {
        delete[] graph->matrix[i];
    }
    delete[] graph->matrix;
    delete graph;
}

void makeForOneTown(Graph *graph, int firstTown)
{
    int *distance = new int [graph->size];
    bool *seenTops = new bool [graph->size];
    for (int i = 0; i < graph->size; i++)
    {
        distance[i] = infinity;
        seenTops[i] = false;
    }
    distance[firstTown] = 0;
    int minDistance = 0;
    int minTown = 0;
    int temp = 0;
    do
    {
        minDistance = infinity;
        minTown = infinity;
        for (int i = 0; i < graph->size; i++)
        {
            if (seenTops[i] == false && distance[i] < minDistance)
            {
                minDistance = distance[i];
                minTown = i;
            }
        }

        if (minTown != infinity)
        {
            for (int i = 0; i < graph->size; i++)
            {
                if (graph->matrix[minTown][i] > 0)
                {
                    temp = minDistance + graph->matrix[minTown][i];
                    if (temp < distance[i])
                    {
                        distance[i] = temp;
                    }
                }
            }
            seenTops[minTown] = true;
        }
    } while (minTown < infinity);

    for (int i = 0; i < graph->size; i++)
    {
        graph->matrix[firstTown][i] = distance[i];
    }
    delete[] distance;
    delete[] seenTops;
}

void algorithmDijkstra(Graph *graph)
{
    for (int i = 0; i < graph->size; i++) // find min distance between towns
    {
        makeForOneTown(graph, i);
    }
}


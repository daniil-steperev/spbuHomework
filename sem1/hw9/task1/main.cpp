#include <iostream>
#include <fstream>
#include "graph.h"
#include "cities.h"
using namespace std;

const int sizeOfFile = 1200;

int main()
{
    char *fileName = new char[sizeOfFile];
    cout << "Input file name: ";
    cin >> fileName;

    ifstream file(fileName);
    delete[]fileName;
    if (!file.is_open())
    {
        cout << "File can not be opened!" << endl;
        return 0;
    }

    int sizeOfGraph = 0;
    int numberOfRoads = 0;
    file >> sizeOfGraph >> numberOfRoads;

    Graph *graph = createGraph(sizeOfGraph); // fill graph
    getGraph(graph, numberOfRoads, file);

    int numberOfCapitals = 0;
    file >> numberOfCapitals;
    int *capitals = new int[numberOfCapitals] {0};

    for (int i = 0; i < numberOfCapitals; i++) // fill capitals
    {
        file >> capitals[i];
    }

    algorithmDijkstra(graph); // count minimal distance from each city to another one
    printCitiesList(graph, capitals, numberOfCapitals); // print cities and capitals

    file.close();
    delete[] capitals;
    deleteGraph(graph);
    return 0;
}

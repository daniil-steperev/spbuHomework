#include <iostream>
#include "graph.h"
#include "cities.h"
#include "list.h"
using namespace std;

const int infinity = 1000000;

bool isTrueCondition(bool *usedCities, Graph *graph, int firstTown, int lastTown, int minimumDistance)
{
    return (graph->matrix[firstTown][lastTown] < minimumDistance && usedCities[lastTown] == false && graph->matrix[firstTown][lastTown] != 0);
}

int returnMinimumTown(Graph *graph, int firstTown, bool *usedCities)
{
    int minimumDistance = infinity;
    for (int i = 0; i < graph->size; i++)
    {
        if (isTrueCondition(usedCities, graph, firstTown, i, minimumDistance))
        {
            minimumDistance = graph->matrix[firstTown][i];
        }
    }

    for (int i = 0; i < graph->size; i++)
    {
        if (graph->matrix[firstTown][i] == minimumDistance)
        {
            return i;
        }
    }
}

int returnCityDistribution(bool *usedCities, Graph *graph, int town)
{
    int newTown = returnMinimumTown(graph, town, usedCities);
    usedCities[newTown] = true;
    return newTown;
}

void printCitiesList(Graph *graph, int *capitals, int numberOfCapitals)
{
    bool *usedCities = new bool[graph->size] {false};

    List **cityOwnerships = new List *[numberOfCapitals] {nullptr};
    for (int i = 0; i < numberOfCapitals; i++)
    {
        cityOwnerships[i] = createList();
    }

    addToList(cityOwnerships[0], 1);
    usedCities[0] = true;
    int town = 0;;
    int currentTown = 1;

    for (int i = 0; i < graph->size - 1; i++) // "-1" because we already added one town
    {
        town = returnCityDistribution(usedCities, graph, town);
        addToList(cityOwnerships[currentTown], town + 1); // "+ 1" as we want to get answer numerating from "1"
        currentTown = (currentTown + 1) % numberOfCapitals;
    }

    for (int i = 0; i < numberOfCapitals; i++) // print capitals and towns
    {
        cout << "Capital " << capitals[i] << " includes: ";
        printList(cityOwnerships[i]);
        cout << endl;
    }

    for (int i = 0; i < numberOfCapitals; i++)
    {
        deleteList(cityOwnerships[i]);
    }

    delete[] usedCities;
    delete[] cityOwnerships;
}

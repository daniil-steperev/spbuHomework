#include <iostream>
#include "graph.h"

const int infinity = 100000000000;

int getFreeIndex(int *array, int size, int firstCity)
{
    for (int i = 0; i < size; i++)
    {
        if (firstCity == 0)
        {
            if (array[i] == 0 && i > 0)
            {
                return i;
            }
        }
        else
        {
            if (array[i] == 0)
            {
                return i;
            }
        }
    }
    return -1;
}

int **createCitiesList(int numberOfCapitals, int maxNumberOfCities)
{
    int **cityOwnership = new int* [numberOfCapitals];
    for (int i = 0; i < numberOfCapitals; i++)
    {
        cityOwnership[i] = new int [maxNumberOfCities] {0};
    }
    return cityOwnership;
}

void deleteCitiesList(int **cityOwnership, int numberOfCapitals)
{
    for (int i = 0; i < numberOfCapitals; i++)
    {
        delete[] cityOwnership[i];
    }
    delete[] cityOwnership;
}

void addToCities(int **cities, int numberOfCapitals, int capitalIndex, int city, int *capitals)
{
    int index = getFreeIndex(cities[capitalIndex], numberOfCapitals, capitals[capitalIndex]);
    cities[capitalIndex][index] = city;
}

void addCapitals(int **cityOwnership, bool *seenCities, int numberOfCapitals, int *capitals)
{
    for (int i = 0; i < numberOfCapitals; i++)
    {
        addToCities(cityOwnership, numberOfCapitals, i, capitals[i], capitals);
        seenCities[capitals[i]] = true;
    }
}

bool *createSeenCities(int size)
{
    bool *seenCities = new bool [size];
    for (int i = 0; i < size; i++)
    {
        seenCities[i] = false;
    }
    return seenCities;
}

bool isEmpty(bool *seenCities, int size)
{
    for (int i = 0; i < size; i++)
    {
        if (seenCities[i] == false)
        {
            return false;
        }
    }
    return true;
}

int returnNearCity(Graph *graph, bool *seenCities, int *addedCities, int numberOfAdded)
{
    int currentCity = -1;
    int minDistance = infinity;
    int indexNearCity = infinity;

    for (int i = 0; i < numberOfAdded; i++)
    {
        currentCity = addedCities[i];
        for (int j = 0; j < graph->size; j++)
        {
            if (graph->matrix[currentCity][j] != 0 && !seenCities[j] && graph->matrix[currentCity][j] < minDistance)
            {
                minDistance = graph->matrix[currentCity][j];
                indexNearCity = j;
            }
        }
    }

    if (indexNearCity == infinity)
    {
        return -1;
    }
    return indexNearCity;
}

int getNearCity(Graph *graph, int **cityOwnership, bool *seenCities, int currentCapital)
{
    int numberOfAddedCities = getFreeIndex(cityOwnership[currentCapital], graph->size, cityOwnership[currentCapital][0]);
    if (numberOfAddedCities == 0)
    {
        numberOfAddedCities++;
    }
    int newCity = returnNearCity(graph, seenCities, cityOwnership[currentCapital], numberOfAddedCities);
    return newCity;
}

void printCities(int **cityOwnership, int numberOfCapitals, int size)
{
    for (int i = 0; i < numberOfCapitals; i++)
    {
        cout << "Capital " << cityOwnership[i][0] + 1 << ": ";
        cout << cityOwnership[i][0] + 1 << ' ';
        for (int j = 1; j < size; j++)
        {
            if (cityOwnership[i][j] > 0)
            {
                cout << cityOwnership[i][j] + 1 << ' ';
            }
        }
        cout << endl;
    }
}

void printCitiesAndCapitals(Graph *graph, int *capitals, int numberOfCapitals)
{
    int **cityOwnership = createCitiesList(numberOfCapitals, graph->size);
    bool *seenCities = createSeenCities(graph->size);

    addCapitals(cityOwnership, seenCities, numberOfCapitals, capitals);

    int currentCapital = 0;
    while (!isEmpty(seenCities, graph->size))
    {
        int newTown = getNearCity(graph, cityOwnership, seenCities, currentCapital);
        if (newTown != - 1)
        {
            addToCities(cityOwnership, numberOfCapitals, currentCapital, newTown, capitals);
            seenCities[newTown] = true;
        }
        currentCapital = (currentCapital + 1) % numberOfCapitals;
    }

    printCities(cityOwnership, numberOfCapitals, graph->size);

    deleteCitiesList(cityOwnership, numberOfCapitals);
    delete[] seenCities;
}

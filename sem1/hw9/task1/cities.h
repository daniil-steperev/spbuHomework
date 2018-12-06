#pragma once
#include "graph.h"

int returnMinimumTown(Graph *graph, int firstTown, bool *usedCities);
int returnCityDistribution(bool *usedCities, Graph *graph, int town);

void printCitiesList(Graph *graph, int *capitals, int numberOfCapitals);


#pragma once
#include "graph.h"


void addToCities(int **cities, int numberOfCapitals, int capitalIndex, int city, int *capitals);
void addCapitals(int **cityOwnership, bool *seenCities, int numberOfCapitals, int *capitals);

int **createCitiesList(int numberOfCapitals, int maxNumberOfCities);
void deleteCitiesList(int **cityOwnership, int numberOfCapitals);
bool *createSeenCities(int size);

int getFreeIndex(int *array, int size, int firstCity);
bool isEmpty(bool *seenCities, int size);

int returnNearCity(Graph *graph, bool *seenCities, int *addedCities, int numberOfAdded);
int getNearCity(Graph *graph, int **cityOwnership, bool *seenCities, int currentCapital);
void printCities(int **cityOwnership, int numberOfCapitals, int size);

void printCitiesAndCapitals(Graph *graph, int *capitals, int numberOfCapitals);

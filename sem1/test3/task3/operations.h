#pragma once
#include <fstream>

using namespace std;

void findVertexes(int **matrix, int numberOfElements, int numberOfWays);
bool suitableVertex(bool *used, int numberOfElements);
void findAnotherWays(int **matrix, bool *used, int numberOfElements, int index, int numberOfWays, int currentVertex);
int findNewVertex(int **matrix, int numberOfElements, int currentVertex);
void resetValues(bool *used, int numberOfElements);
int **createMatrix(ifstream &file, int numberOfElements, int numberOfWays);
void deleteMatrix(int** matrix, int numberOfElements, int numberOfWays);

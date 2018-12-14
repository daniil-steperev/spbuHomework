#pragma once
#include <fstream>

using namespace std;

void fillMatrix(ifstream &file, int **matrix, int numberOfStrings, int numberOfColumns);
void deleteMatrix(int **&matrix, int numberOfStrins);

void sortByColumn(int **matrix, int numberOfString, int indexOfColumn);
int returnMinInString(int **matrix, int numberOfColumn, int indexOfString);

void printSaddlePoints(int **matrix, int numberOfStrings, int numberOfColumns);

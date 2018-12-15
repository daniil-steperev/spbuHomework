#pragma once
#include "graph.h"
#include "aStar.h"

bool **createBoolMatrix(int size);
int **createIntMatrix(int size);

void deleteBoolMatrix(int size, bool **matrix);
void deleteIntMatrix(int size, int **matrix);

bool isInsideTheBorder(int size, int x, int y); // checks whether a point is inside the border
void checkAdjacent(int **matrix, int size, int **distances, bool **used, int **previousHeightParametres, int **previousWidthParametres, int x, int y, int newX, int newY); // checks adjacent point
bool isCorrectPoint(int **matrix, int size, int **distances, int newX, int newY, bool **used, int minF, int y, int x, Node &finish);
void checkAdjacentPoint(int **matrix, int size, int **distances, bool **used, Node &finish, Node &current, int &minF, int y, int x, int newY, int newX);

void changeDistances(int **matrix, int size, int **distances, bool **used, int **previousHeightParametres, int **previousWidthParametres, Node &point);
void changeValue(int **matrix, int size, int **distances, bool **used, Node &finish, Node &current, int &minF, int y, int x);

void printAnswerMatrix(int **matrix, int size, Node &start, Node &finish);

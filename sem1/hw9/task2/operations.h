#pragma once
#include <fstream>
#include "list.h"
#include "binaryTree.h"
#include "queue.h"
using namespace std;

void getElements(char *line, int *alphabet);
void addElementsToQueue(int *alphabet, PriorityQueue *queue);
void printTableOfFrequency(ofstream &file, int *alphabet);

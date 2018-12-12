#pragma once
#include <fstream>
using namespace std;
struct Node;

struct List;
struct ListElement;

List *createList();
void deleteList(List *list);

void addToList(List *list, char symbol, string str);

bool isEmpty(List *list);
void writeElementInChar(ofstream &file, List *list, string symbol);
void printList(List *list);

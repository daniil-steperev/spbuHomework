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
void writeElementInCode(ofstream &file, List *list, char symbol);

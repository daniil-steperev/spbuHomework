#pragma once
#include <fstream>
#include "myString.h"

using namespace std;
struct Node;

struct List;
struct ListElement;

List *createList();
void deleteList(List *list);

void addToList(List *list, char symbol, MyString *str);

bool isEmpty(List *list);
void writeElementInCode(ofstream &file, List *list, char symbol);

#pragma once
using namespace std;

struct List;
struct ListElement;

List *createList();
void deleteList(List *list);

void add(List *list, int element);

bool isEmpty(List *list);
void printList(List *list);
void printSortedWithQuantities(List *list);

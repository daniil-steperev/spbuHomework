#pragma once

struct List;
struct ListElement;

List *createList();

void addToList(List *list, int element);
void printList(List *list);
void deleteList(List *list);

bool isEmpty(List *list);

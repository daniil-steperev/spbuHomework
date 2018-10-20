#pragma once

struct List;
List *createList();
struct ListElement;
ListElement *makeListElement(char *name, int phone, ListElement *next);

void addToList(List *list, char *name, int phone);

int sizeOfList(List *list);

char *get(List *list, int number, int &phone);

int findPhoneByName(List *list, char *name);
char *findNameByPhone(List *list, int phone);

bool isEmpty(List *list);

void clearList(List *list);
void deleteList(List *list);

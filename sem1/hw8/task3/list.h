#pragma once
#include "myString.h"

struct ListElement {
    MyString *content;
    int quantity;
    ListElement *next;
};

struct List {
    ListElement *first;
    int size;
};

List *createList();
void deleteList(List *list);
void printList(List *list);

void add(List *list, MyString *content);
void removeListElement(List *list, MyString *content);

#pragma once

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    ListElement *first;
    int length;
};

List *createList();
ListElement *getElement(List *list, int index);
int getValueOfElement(List *list, int index);
void deleteList(List *list);
void add(List *list, int element);
bool isEmpty(List *list);
void insertSort(List *list);
void printList(List *list);
void changeElements(List *list, int first, int second);

#pragma once

struct ListElement
{
    int value;
    ListElement *next;
    ListElement *previous;
};

struct List
{
    ListElement *head;
    ListElement *tail;
    int length;
};

List *createList();
void deleteList(List *list);

void add(List *list, int element);

bool isEmpty(List *list);
bool isSymmetricList(List *list);

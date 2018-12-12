#include <iostream>
#include "list.h"
#include <string.h>
using namespace std;

struct ListElement
{
    char value;
    string code;
    ListElement *next;
};

struct List
{
    ListElement *first;
};

List *createList()
{
    List *list = new List{nullptr};
    return list;
}

void addToList(List *list, char symbol, string str)
{
    ListElement *newElement = new ListElement;
    newElement->value = symbol;
    newElement->code = str;
    newElement->next = nullptr;

    if (isEmpty(list))
    {
        list->first = newElement;
    }
    else
    {
        ListElement *current = list->first;
        while (current->next != nullptr)
        {
            current = current->next;
        }
        current->next = newElement;
    }
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }
    delete list;
}

bool isEmpty(List *list)
{
    return list->first == nullptr;
}

void writeElementInChar(ofstream &file, List *list, string code)
{
    ListElement *current = list->first;
    while (current && current->code != code)
    {
        current = current->next;
    }

    file << current->value;
}



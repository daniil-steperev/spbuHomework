#include <iostream>
#include "list.h"
#include <string.h>
using namespace std;

struct ListElement
{
    int value;
    int quantity;
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

void add(List *list, int element)
{
    if (isEmpty(list))
    {
        ListElement *newElement = new ListElement {element, 1, nullptr};
        list->first = newElement;
        return;
    }

    ListElement *current = list->first;
    while (current->next && current->value < element)
    {
        current = current->next;
    }

    if (current->value == element)
    {
        current->quantity++;
        return;
    }

    if (current->value < element)
    {
        current->next = new ListElement {element, 1, nullptr};
    }
    else
    {
        ListElement *newElement = new ListElement {element, 1, current->next};
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

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->value << " - " << current->value << endl;
        current = current->next;
    }
}

void printSortedWithQuantities(List *list)
{
    if (isEmpty(list))
    {
        cout << "List is empty!";
        return;
    }
    ListElement *current = list->first;
    cout << "Sorted elements (value{quantity}): ";
    while (current)
    {
        cout << current->value << '{' << current->quantity << '}' << ' ';
        current = current->next;
    }
}

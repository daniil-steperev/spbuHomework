#include <iostream>
#include "list.h"
using namespace std;

struct ListElement
{
    int element;
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

bool isEmpty(List *list)
{
    return (list->first== nullptr);
}

void addToList(List *list, int element)
{
    if (isEmpty(list))
    {
        list->first = new ListElement{element, nullptr};
    }
    else
    {
        ListElement *current = list->first;
        while (current->next)
        {
            current = current->next;
        }
        current->next = new ListElement{element, nullptr};
    }
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << current->element << ' ';
        current = current->next;
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

#include <iostream>
#include "list.h"
using namespace std;

List *createList()
{
    return new List {nullptr, 0};
}

void deleteList(List *list)
{
    if (isEmpty(list))
    {
        delete list;
        return;
    }

    ListElement *current = list->first;
    while (current)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }
    delete list;
}

void printList(List *list)
{
    if (isEmpty(list))
    {
        cout << "List is empty!";
        return;
    }

    ListElement *current = list->first;
    while (current)
    {
        cout << current->value << ' ';
        current = current->next;
    }
}

void add(List *list, int element)
{
    list->length++;

    if (isEmpty(list))
    {
        ListElement *newElement = new ListElement {element, nullptr, nullptr};
        list->first = newElement;
        return;
    }

    if (list->first->next == nullptr)
    {
        list->first->next = new ListElement{element, nullptr, nullptr};
        return;
    }

    ListElement *firstNext = list->first->next;
    ListElement *newElement = new ListElement{element, firstNext, list->first};
    firstNext->previous = newElement;
    list->first->next = newElement;
}

int getValueOfElement(List *list, int index)
{
    ListElement *current = list->first;
    for (int i = 1; i <= index; i++)
    {
        current = current->next;
    }
    return current->value;
}

void swapElements(ListElement *first, ListElement *second)
{
    int value = first->value;
    first->value = second->value;
    second->value = value;
}

void selectionSort(List *list)
{
    if (isEmpty(list) || list->length == 1)
    {
        return;
    }
    int size = list->length - 1;
    ListElement *minElement = list->first;
    ListElement *current = list->first;

    for (int i = 0; i < size - 1; i++)
    {
        current = list->first;
        minElement = list->first;
        for (int j = i + 1; j < size; j++)
        {
            if (minElement->value < current->value)
            {
                minElement = current;
            }
            current = current->next;
        }
        swapElements(minElement, current);
    }
    delete minElement;
    delete current;
}

bool isEmpty(List *list)
{
    return (list->first == nullptr);
}

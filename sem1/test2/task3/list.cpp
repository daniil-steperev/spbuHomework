#include "list.h"
#include <iostream>
using namespace std;

List *createList()
{
    return new List {nullptr, nullptr, 0};
}

void deleteList(List *list)
{
    if (isEmpty(list))
    {
        delete list;
        return;
    }

    ListElement *current = list->head;
    while (current != nullptr)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }

    delete list;
}

void add(List *list, int element)
{
    list->length++;

    if (isEmpty(list))
    {
        ListElement *newElement = new ListElement{element, nullptr, nullptr};
        list->head = newElement;
        list->head->next = list->tail;

        list->tail = newElement;
        list->tail->previous = list->head;
    }
    else
    {
        ListElement *previousTail = list->tail;
        ListElement *newElement = new ListElement{element, nullptr, nullptr};
        list->tail = newElement;
        list->tail->previous = previousTail;
        previousTail->next = list->tail;
    }
}

bool isEmpty(List *list)
{
    return (list->head == nullptr);
}


bool isSymmetricList(List *list)
{
    if (isEmpty(list))
    {
        return true;
    }

    ListElement *leftCurrent = list->head;
    ListElement *rightCurrent = list->tail;
    int numberOfChecks = list->length / 2; // "/ 2" because we are going to check from start and end simultaneously
    for (int i = 0; i < numberOfChecks; i++)
    {
        if (leftCurrent->value != rightCurrent->value)
        {
            return false;
        }

        leftCurrent = leftCurrent->next;
        rightCurrent = rightCurrent->previous;
    }
    return true;
}

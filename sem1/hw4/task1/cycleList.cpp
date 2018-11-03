#include <stdio.h>
#include "cycleList.h"

struct CycleListElement
{
    int value;
    CycleListElement *next;
};

struct SortedCycleList
{
    CycleListElement *first;
    int length;
    CycleListElement *position;
};

SortedCycleList *createSortedCycleList()
{
    SortedCycleList *list = new SortedCycleList{nullptr, 0, nullptr};
    return list;
}

CycleListElement *createElement(int value, CycleListElement *next)
{
    CycleListElement *newElement = new CycleListElement{value, next};
    return newElement;
}

void addToEmptyList(SortedCycleList *list, int value)
{
    list->first = createElement(value, nullptr);
    list->first->next = list->first;
    list->position = list->first;
    list->length += 1;
}

void addFirst(SortedCycleList *list, int value)
{
    CycleListElement *current = list->first;
    while (current->next != list->first)
    {
        current = current->next;
    }
    list->first = createElement(value, list->first);
    if (list->position->next == list->first->next)
    {
        list->position = list->first;
    }
    current->next = list->first;
    list->length += 1;
}

void add(SortedCycleList *list, int value)
{
    if (list->first == nullptr)
    {
        addToEmptyList(list, value);
        return;
    }
    if (list->first->value > value)
    {
        addFirst(list, value);
        return;
    }
    CycleListElement *current = list->first;
    while ((current->next != list->first) && (current->next->value < value))
    {
        current = current->next;
    }
    current->next = createElement(value, current->next);
    if (current == list->position)
    {
        list->position = current->next;
    }
    list->length += 1;
}

void removeNext(CycleListElement *current)
{
    CycleListElement *deleteElement = current->next;
    current->next = deleteElement->next;
    delete deleteElement;
}

void removeFirst(SortedCycleList *list)
{
    CycleListElement *current = list->first;
    while (current->next != list->first)
    {
        current = current->next;
    }
    if (list->position == current->next)
    {
        list->position = current;
    }
    removeNext(current);
    list->first = current->next;
    list->length -= 1;
}

void removeElement(SortedCycleList *list, int number)
{
    if (list->first == nullptr)
    {
        return;
    }
    CycleListElement *current = list->first;
    if (number % list->length == 0)
    {
        removeFirst(list);
        return;
    }
    if (current->next == current)
    {
        delete current;
        return;
    }
    for (int i = 0; i < number - 1; i++)
    {
        current = current->next;
    }
    if (list->position == current->next)
    {
        list->position = current;
    }
    removeNext(current);
    list->length -= 1;
}

void removeByPosition(SortedCycleList *list)
{
    if (list->first == nullptr)
    {
        return;
    }
    if (list->position->next == list->first)
    {
        removeFirst(list);
        return;
    }
    removeNext(list->position);
}

void changePosition(SortedCycleList *list, int shift)
{
    for (int i = 0; i < shift; ++i)
    {
        list->position = list->position->next;
    }
}

int getLast(SortedCycleList *list, int number)
{
    CycleListElement *current = list->first;
    for (int i = 0; i < number; i++)
    {
        current = current->next;
    }
    return current->value;
}

void deleteCycleList(SortedCycleList *list)
{
    CycleListElement *current = list->first->next;
    while (current != list->first)
    {
        CycleListElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }
    delete current;
    delete list;
}

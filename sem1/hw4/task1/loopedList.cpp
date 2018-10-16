#include <stdio.h>
#include "loopedList.h"

struct ListElement
{
    int value;
    ListElement *next;
};

struct LoopedList
{
    ListElement *first;
    int length;
    ListElement *position;
};

LoopedList *createLoopedList()
{
    LoopedList *list = new LoopedList{nullptr, 0, nullptr};
    return list;
}

ListElement *createElement(int value, ListElement *next)
{
    ListElement *newElement = new ListElement{value, next};
    return newElement;
}

void addToEmptyList(LoopedList *list, int value)
{
    list->first = createElement(value, nullptr);
    list->first->next = list->first;
    list->position = list->first;
    list->length += 1;
}

void addFirst(LoopedList *list, int value)
{
    ListElement *current = list->first;
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

void add(LoopedList *list, int value)
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
    ListElement *current = list->first;
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

void removeNext(ListElement *current)
{
    ListElement *deleteElement = current->next;
    current->next = deleteElement->next;
    delete deleteElement;
}

void removeFirst(LoopedList *list)
{
    ListElement *current = list->first;
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

void removeElement(LoopedList *list, int number)
{
    if (list->first == nullptr)
    {
        return;
    }
    ListElement *current = list->first;
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

void removeByPosition(LoopedList *list)
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

void changePosition(LoopedList *list, int shift)
{
    for (int i = 0; i < shift; ++i)
    {
        list->position = list->position->next;
    }
}

int getLast(LoopedList *list, int number)
{
    ListElement *current = list->first;
    for (int i = 0; i < number; i++)
    {
        current = current->next;
    }
    return current->value;
}

void deleteLoopedList(LoopedList *list)
{
    ListElement *current = list->first->next;
    while (current != list->first)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }
    delete current;
    delete list;
}

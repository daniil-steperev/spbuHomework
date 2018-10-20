#include <stdio.h>
#include <string.h>
#include "list.h"

struct ListElement
{
    char *name;
    int phone;
    ListElement *next;
};

struct List
{
    ListElement *first;
    int length;
};

List *createList()
{
    List *list = new List{nullptr, 0};
    return list;
}

ListElement *makeListElement(char *name, int phone, ListElement *next)
{
    ListElement *newElement = new ListElement{name, phone, next};
    return newElement;
}

void addToList(List *list, char *name, int phone)
{
    if (isEmpty(list))
    {
        list->first = makeListElement(name, phone, nullptr);
        list->length += 1;
        return;
    }
    if (list->first->phone > phone)
    {
        list->first = makeListElement(name, phone, list->first);
        list->length += 1;
        return;
    }
    ListElement *current = list->first;
    while (current->next && current->next->phone < phone)
    {
        current = current->next;
    }
    current->next = makeListElement(name, phone, current->next);
    list->length += 1;
}

int sizeOfList(List *list)
{
    return list->length;
}

char *get(List *list, int number, int &phone)
{
    if (number >= sizeOfList(list))
    {
        return "";
    }
    ListElement *current = list->first;
    for (int i = 0; i < number; ++i)
    {
        current = current->next;
    }
    phone = current->phone;
    return current->name;
}

int findPhoneByName(List *list, char *name)
{
    if (isEmpty(list))
    {
        return -1;
    }
    ListElement *current = list->first;
    while (current && strcoll(current->name, name) != 0)
    {
        current = current->next;
    }
    if (current == nullptr)
    {
        return -1;
    }
    return current->phone;
}

char *findNameByPhone(List *list, int phone)
{
    if (isEmpty(list))
    {
        return "";
    }
    ListElement *current = list->first;
    while (current && current->phone < phone)
    {
        current = current->next;
    }
    if (current == nullptr || current->phone != phone)
    {
        return "";
    }
    return current->name;
}

void deleteList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete[] deleteElement->name;
        delete deleteElement;
    }
    delete list;
}

bool isEmpty(List *list)
{
    return list->first == nullptr;
}

void clearList(List *list)
{
    if (isEmpty(list))
    {
        return;
    }
    ListElement *current = list->first;
    while (current)
    {
        ListElement *deleteElement = current;
        current = current->next;
        delete[] deleteElement->name;
        delete deleteElement;
    }
    list->length = 0;
    list->first = nullptr;
}

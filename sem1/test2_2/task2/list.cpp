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
        list->first = new ListElement {element, nullptr};
        return;
    }

    ListElement *current = list->first;
    while (current->next)
    {
        current = current->next;
    }

    current->next = new ListElement{element, nullptr};
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

ListElement *getElement(List *list, int index)
{
    ListElement *current = list->first;
    int counter = 0;
    for (int i = 1; i <= index; i++)
    {
        current = current->next;
    }
    return current;
}

void insertSort(List *list)
{
    if (isEmpty(list) || list->length == 1)
    {
        return;
    }

    int length = list->length;
    for (int j = 1; j <= length; j++)
    {
        int i = j - 1;
        while (i > 0 && getValueOfElement(list, i - 1) > getValueOfElement(list, i))
        {
            changeElements(list, i, i - 1);
            i--;
        }
    }
}

void changeElements(List *list, int first, int second)
{
    ListElement *firstElement = getElement(list, first);
    ListElement *secondElement = getElement(list, second);

    int secondValue = secondElement->value;
    secondElement->value = firstElement->value;
    firstElement->value = secondValue;
}

bool isEmpty(List *list)
{
    return (list->first == nullptr);
}

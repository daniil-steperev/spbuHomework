#include <iostream>
#include "list.h"
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

void writeElementInCode(ofstream &file, List *list, char symbol)
{
    ListElement *current = list->first;
    while (current->value != symbol)
    {
        current = current->next;
    }
	string code = current->code;
    file << code << ' ';
}

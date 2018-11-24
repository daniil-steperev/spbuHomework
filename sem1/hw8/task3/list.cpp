#include <iostream>
#include "list.h"
#include "myString.h"

using namespace std;

List *createList()
{
    return new List {nullptr, 0};
}

bool isEmptyList(List *list)
{
    return (list->size == 0);
}

void deleteList(List *list)
{
    while (!isEmptyList(list))
    {
		deleteString(list->first->content);
        ListElement *nextElement = list->first;
        list->first = list->first->next;
        delete nextElement;
		list->size--;
    }
    delete list;
}

void removeListElement(List *list, MyString *str)
{
    ListElement *current = list->first;
    if (list->first == nullptr)
    {
        return;
    }
    while (current->next && are_Equal(current->next->content, str))
    {
        current = current->next;
    }
    if (current->quantity > 1)
    {
        current->quantity--;
        return;
    }
    ListElement *nextElement = current->next; // if only one such element
    current->next = nextElement->next;
    deleteString(nextElement->content);
    list->size--;
    delete nextElement;
}

void add(List *list, MyString *str)
{
    ListElement *current = list->first;
    if (list->first == nullptr)
    {
        ListElement *newElement = new ListElement {str, 1, nullptr};
        list->first = newElement;
        list->size++;
        return ;
    }
    while (current->next)
    {
        if (are_Equal(current->content, str)) // element already added
        {
            current->quantity++;
            return;
        }
        current = current->next;
    }
    ListElement *newElement = new ListElement {str, 1, nullptr};
    current->next = newElement;
    list->size++;
}

void printList(List *list)
{
    ListElement *current = list->first;
    while (current)
    {
        cout << returnChar(current->content) << " ";
        current = current->next;
    }
    cout << endl;
}

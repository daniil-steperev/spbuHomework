#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    List *numbers = createList();
    cout << "Enter number of elements: ";
    int numberOfElements = 0;
    cin >> numberOfElements;
    cout << "Enter elements: ";
    for (int i = 0; i < numberOfElements; i++)
    {
        int element = 0;
        cin >> element;
        add(numbers, element);
    }

    insertSort(numbers);
    cout << "Sorted list: ";
    printList(numbers);

    deleteList(numbers);
    return 0;
}

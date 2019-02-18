#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    List *numbers = createList();
    int numberOfElements = 0;
    cout << "Enter number of elements: ";
    cin >> numberOfElements;

    cout << "Enter elements: ";
    for (int i = 0; i < numberOfElements; i++)
    {
        int number = 0;
        cin >> number;
        add(numbers, number);
    }

    selectionSort(numbers);
    printList(numbers);

    deleteList(numbers);
    return 0;
}

#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    List *numbers = createList();
    cout << "Enter numbers (0 = end): ";
    int number = -1;
    while (number != 0)
    {
        cin >> number;
        if (number == 0)
        {
            break;
        }
        add(numbers, number);
    }

    printSortedWithQuantities(numbers);
    deleteList(numbers);
}

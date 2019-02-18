#include <iostream>
#include "queue.h"

using namespace std;

void menu()
{
    cout << "0 - exit" << endl;
    cout << "1 - enqueue" << endl;
    cout << "2 - dequeue" << endl;
}

int main()
{
    Queue *numbers = createQueue();

    int task = -1;
    int number = 0;
    int priority = 0;
    while (task != 0)
    {
        menu();
        cout << "Enter your task: ";
        cin >> task;

        switch (task)
        {
            case 0:
               cout << "Thanks for using my program!";
               break;
            case 1:
                cout << "Enter an element and a priority: ";
                cin >> number >> priority;
                enqueue(numbers, number, priority);
                break;
            case 2:
                cout << "Popped element: " << dequeue(numbers) << endl;
                break;
        }
    }

    deleteQueue(numbers);
    return 0;
}

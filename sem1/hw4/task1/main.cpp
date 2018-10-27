#include <iostream>
#include "cycleList.h"

using namespace std;

bool test2()
{
    int n = 6;
    int m = 3;
    SortedCycleList *list = createSortedCycleList();
    for (int i = 1; i <= n; ++i) // starting with '1' to get right index (the first one in the task called '1' but not '0'
    {
        add(list, i);
    }
    for (int i = 0; i < n - 1; ++i)
    {
        changePosition(list, m - 1);
        removeByPosition(list);
    }
    int answer = getLast(list, 0);
    deleteLoopedList(list);
    return answer == 1;
}

bool test1()
{
    int n = 10;
    int m = 4;
    SortedCycleList *list = createSortedCycleList();
    for (int i = 1; i <= n; ++i) // starting with '1' to get right index (the first one in the task called '1' but not '0'
    {
        add(list, i);
    }
    for (int i = 0; i < n - 1; ++i)
    {
        changePosition(list, m - 1);
        removeByPosition(list);
    }
    int answer = getLast(list, 0);
    deleteLoopedList(list);
    return answer == 5;
}

void circleOfSikariya(int numberOfPeople, int numberOfKilling)
{
    SortedCycleList *warriors = createSortedCycleList();
    for (int i = 1; i <= numberOfPeople; ++i) // starting with '1' to get right index (the first one in the task called '1' but not '0'
    {
        add(warriors, i);
    }
    for (int i = 0; i < numberOfPeople - 1; ++i)
    {
        changePosition(warriors, numberOfKilling - 1);
        removeByPosition(warriors);
    }
    int answer = getLast(warriors, 0);
    printf("The position of last warrior: %d", answer);
    deleteLoopedList(warriors);
}

int main()
{
    int numberOfPeople = 0;
    printf("Enter the number of the people: ");
    scanf("%d", &numberOfPeople);
    int numberOfKilling = 0;
    printf("Enter the number of killing: ");
    scanf("%d", &numberOfKilling);
    if (test1 && test2)
    {
        circleOfSikariya(numberOfPeople, numberOfKilling);
    }
    else
    {
        if (!test1())
        {
            cout << "Test 1 failed!";
            if (!test2())
            cout << "\nTest 2 failed!" << endl;
        }
        else
        {
            cout << "Test 2 failed!" << endl;
        }
    }
    return 0;
}

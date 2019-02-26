#include <iostream>
#include "cycleList.h"
#include "circleOfSikariya.h"

using namespace std;

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
    deleteCycleList(warriors);
}

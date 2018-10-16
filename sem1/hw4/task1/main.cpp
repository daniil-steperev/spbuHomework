#include <stdio.h>
#include "loopedList.h"

bool test2()
{
    int n = 6;
    int m = 3;
    LoopedList *list = createLoopedList();
    for (int i = 1; i <= n; ++i)
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
    LoopedList *list = createLoopedList();
    for (int i = 1; i <= n; ++i)
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
    LoopedList *warriors = createLoopedList();
    for (int i = 1; i <= numberOfPeople; ++i)
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
    if (test1)
    {
        printf("Test 1 successfully passed.\n");
    }
    else
    {
        printf("Test 1 failed.\n");
    }
    if (test2)
    {
        printf("Test 2 successfully passed.\n");
    }
    else
    {
        printf("Test 2 failed.\n");
    }
    if (test1 && test2)
    {
        circleOfSikariya(numberOfPeople, numberOfKilling);
    }
    return 0;
}

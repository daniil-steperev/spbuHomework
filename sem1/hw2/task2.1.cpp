#include <stdio.h>

int upperingIndex(int *array, int number)
{
    for (int i = 0; i < number; ++i)
    {
        if (array[i] == 0)
            return i;
    }
}

int underingIndex(int *array, int number)
{
    for (int i = number - 2; i > 0; --i)
    {
        if (array[i] > array[i + 1] && array[i] != 0 && array[i] != 1)
            return i;
    }
}

void print(int *array, int number)
{
    printf("%d = ", number);
    for (int i = 0; i < number; ++i)
    {
        if (array[i] != 0)
        {
            if (i == number || array[i + 1] == 0)
                printf("%d", array[i]);
            else
                printf("%d + ", array[i]);
        }
    }
    printf("\n");
}

void copying(int *newArray, int *previousArray, int length)
{
    for (int i = 0; i < length; ++i)
    {
        newArray[i] = previousArray[i];
    }
}

bool checkingForAComb(int *array, int number)
{
    for (int i = 1; i < number; ++i)
    {
        if (array[i] > 1)
            return true;
    }
    return false;
}

void combination(int *previousArray, int number)
{
    int* array = new int[number] {0};
    copying(array, previousArray, number);
    print(previousArray, number);
    while(checkingForAComb(array, number))
    {
        int undPos = underingIndex(array, number);
        array[undPos] -= 1;
        int upPos = upperingIndex(array, number);
        array[upPos] += 1;
        print(array, number);
    }
    copying(array, previousArray, number);
    array[0] -= 1;
    if (number - array[0] <= array[0])
    {
        array[1] = number - array[0];
        combination(array, number);
    }
    else if (number - array[0] > array[0] && array[0] > 0)
    {
        array[1] = array[0];
        int sum = number - array[0] * 2;
        for (int i = 2; i < number; ++i)
        {
            if (sum >= array[0])
            {
                sum -= array[0];
                array[i] = array[0];
            }
            else if (sum > 0)
            {
                array[i] = sum;
                break;
            }
            else if (sum == 0)
                break;
        }
        combination(array, number);
    }
    delete[] array;
}

void firstCombination(int number)
{
    int* array = new int[number] {number};
    combination(array, number);
    delete[] array;
}

int main()
{
    int number = 0;
    printf("Enter the number: ");
    scanf("%d", &number);
    firstCombination(number);
    return 0;
}

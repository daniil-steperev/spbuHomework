#include <stdio.h>

void print(int *array, int length)
{
    for (int i = 0; i < length - 1; ++i)
    {
        printf("%d ", array[i]);
    }
    printf("%d.", array[length - 1]);
}

void quickSort(int *array, int left, int right)
{
    int first = left;
    int last = right;
    int average = array[(left + right) / 2];
    while (left <= right)
    {
        while (array[left] < average)
        {
            left += 1;
        }
        while (array[right] > average)
        {
            right -= 1;
        }
        if (left <= right)
        {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left += 1;
            right -=1;
        }
    }
    if (left < last)
    {
        quickSort(array, left, last);
    }
    if (first < right)
    {
        quickSort(array, first, right);
    }
}

int main()
{
    int length = 0;
    printf("Enter length of the array: ");
    scanf("%d", &length);
    printf("\nEnter the array: ");
    int array[length];
    for (int i = 0; i < length; ++i)
    {
        scanf("%d", &array[i]);
    }
    printf("Entered array: ");
    print(array, length);
    quickSort(array, 0, length - 1);
    printf("\nSorted array: ");
    print(array, length);
    return 0;
}

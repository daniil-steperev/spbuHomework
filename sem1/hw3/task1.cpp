#include <stdio.h>

int searchingMaxNumber(int *array, int length)
{
    for (int i = length - 2; i >= 0; i--)
    {
        if (array[i] == array[i + 1])
            return array[i];
    }
    return -1;
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
    printf("Enter the length of the array: ");
    scanf("%d", &length);
    int* array = new int[length] {0};
    printf("\nEnter the array: ");
    for (int i = 0; i < length; ++i)
        scanf("%d", &array[i]);
    quickSort(array, 0, length - 1);
    int answer = searchingMaxNumber(array, length);
    if (answer != -1)
        printf("%Max number that occurs more than once: %d", answer);
    else
        printf("There is not any number that occurs more than once");
    delete[] array;
    return 0;
}

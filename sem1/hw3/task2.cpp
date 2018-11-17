#include <stdio.h>

bool checkingStrings(char *stringOne, int lengthOfFirst, char *stringTwo, int lengthOfSecond)
{
    if (lengthOfFirst != lengthOfSecond)
        return false;
    for (int i = 0; i < lengthOfFirst; ++i)
    {
        if (stringOne[i] != stringTwo[i])
            return false;
    }
    return true;
}

void quickSort(char *string, int left, int right)
{
    int first = left;
    int last = right;
    int average = string[(left + right) / 2];
    while (left <= right)
    {
        while (string[left] < average)
            left += 1;
        while (string[right] > average)
            right -= 1;
        if (left <= right)
        {
            int tmp = string[left];
            string[left] = string[right];
            string[right] = tmp;
            left += 1;
            right -= 1;
        }
    }
    if (left < last)
        quickSort(string, left, last);
    if (right > first)
        quickSort(string, first, right);
}

int main()
{
    int lengthOfFirst = 0;
    printf("Enter the length of the first string: ");
    scanf("%d", &lengthOfFirst);
    char* stringOne = new char[lengthOfFirst];
    printf("Enter the first string: ");
    scanf("%s", stringOne);

    int lengthOfSecond = 0;
    printf("Enter the length of the second string: ");
    scanf("%d", &lengthOfSecond);
    char* stringTwo = new char[lengthOfSecond];
    printf("Enter the second string: ");
    scanf("%s", stringTwo);

    quickSort(stringOne, 0, lengthOfFirst - 1);
    quickSort(stringTwo, 0, lengthOfSecond - 1);

    if (checkingStrings(stringOne, lengthOfFirst, stringTwo, lengthOfSecond))
        printf("\nYes, you can get the second string by replacing elements in the first");
    else
        printf("\No, you can't get the second string by replacing elements in the first");
    delete[] stringOne;
    delete[] stringTwo;
    return 0;
}

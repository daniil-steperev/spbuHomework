#include <stdio.h>

int newNumber(int *listOfDigits, int length)
{
    int number = 0;
    int multiplier = 1;
    for (int i = length - 1; i >= 0; --i)
    {
        number += listOfDigits[i] * multiplier;
        multiplier *= 10;
    }
    return number;
}

int checkingForZeros(int *listOfDigits, int length)
{
    if (listOfDigits[0] == 0)
    {
        int i = 1;
        while (listOfDigits[i] == 0)
            i += 1;
        int tmp = listOfDigits[0];
        listOfDigits[0] = listOfDigits[i];
        listOfDigits[i] = tmp;
        checkingForZeros(listOfDigits, length);
    }
}

int sortingDigits(int *listOfDigits, int length)
{
    for (int i = 1; i < length; ++i)
    {
        for (int j = 1; j < length; ++j)
        {
            if (listOfDigits[j] < listOfDigits[j - 1])
            {
                int tmp = listOfDigits[j];
                listOfDigits[j] = listOfDigits[j - 1];
                listOfDigits[j - 1] = tmp;
            }
        }
    }
    checkingForZeros(listOfDigits, length);
    int answer = newNumber(listOfDigits, length);
    return answer;
}

int gettingDigits(int number, int length)
{
    int* listOfDigits = new int[length] {0};
    for (int i = 0; i < length; ++i)
    {
        listOfDigits[i] = number % 10;
        number = number / 10;
    }
    int answer = sortingDigits(listOfDigits, length);
    delete[] listOfDigits;
    return answer;
}

int main()
{
    int length = 0;
    printf("Enter length of the number: ");
    scanf("%d", &length);
    int number = 0;
    printf("Enter the number: ");
    scanf("%d", &number);
    printf("Minimal number of that digits is: ");
    printf("%d", gettingDigits(number, length));
    return 0;
}

#include <stdio.h>

int gettingNumber(int *listOfDigits, const length)
{
    int number = 0;
    int multiplier = 1;
    for (int i = length - 1; i > 0; --i)
    {
        number += listOfDigits[i] * multiplier;
        multiplier *= 10;
    }
    return number;
}

int sortingNumber(int number, const length)
{
    int* listOfDigits = new int[number] {0};
    for (int i = 0; i < length; ++i)
    {
        listOfDigits[i] = number % 10;
        number = number / 10;
    }
    for (int i = 1; i < length; ++i)
    {
        for (int j = 0; j < length - i + 1; ++j)
        {
            if (listOfDigits[j] > listOfDigits[j + 1])
            {
                int tmp = listOfDigits[j];
                listOfDigits[j] = listOfDigits[j + 1];
                listOfDigits[j + 1] = tmp;
            }
        }
    }
    int answer = 0;
    answer = gettingNumber(listOfDigits, length);
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
    printf("%d", sortingNumber(number, length));
    return 0;
}

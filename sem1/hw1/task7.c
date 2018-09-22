#include <stdio.h>

void outputOfAllSimple(int n)
{
    int arrayOfNumbers[n];
    for (int i = 0; i < n; ++i)
    {
        arrayOfNumbers[i] = 1;
    }
    arrayOfNumbers[0] = 0;
    for (int i = 2; i < n / 2; ++i)
    {
        if (arrayOfNumbers[i] == 1)
        {
            for (int j = i; j < n; j = j + i)
            {
                if (arrayOfNumbers[i + j] == 1)
                {
                    arrayOfNumbers[i + j] = 0;
                }
            }
        }
    }
    printf("Array of simple: ");
    for (int i = 0; i < n; ++i)
    {
        if (arrayOfNumbers[i] == 1)
        {
            printf("%d ", i);
        }
    }
}

int main()
{
    int number = 0;
    printf("Enter number: ");
    scanf("%d", &number);
    outputOfAllSimple(number);
    return 0;
}

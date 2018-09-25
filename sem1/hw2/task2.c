#include <stdio.h>

void printingUsingOne(int number)
{
    printf("%d = ", number);
    for (int i = 0; i < number; ++i)
    {
        printf("%d ", 1);
        if (i != number - 1)
        {
            printf("+ ");
        }
    }
}

void print(int *combination, int number)
{
    printf("%d = ", number);
    int sum = number;
    for (int i = 0; i < number; ++i)
    {
        if (combination[i] == number)
        {
            printf("%d", number);
            break;
        }
        else if (sum - combination[i] >= 0 && combination[i] != 0)
        {
            printf("%d ", combination[i]);
            sum -= combination[i];
        }

        if (sum > 0)
        {
            printf("+ ");
        }
    }
    printf("\n");
}

void upperingOneDigit(int *lastCombination, int position, int number, int lastNumber)
{
    for (int j = position + 1; j < number; ++j)
    {
        if (lastCombination[j] < lastNumber)
        {
            lastCombination[j] += 1;
            break;
        }
    }
}

int newCombination(int *lastCombination, int number)
{

    int i = 0;
    while (i < number)
    {
        if (lastCombination[i] > 1)
        {
            print(lastCombination, number);
            lastCombination[i] -= 1;
            upperingOneDigit(lastCombination, i, number, lastCombination[i]);
        }
        else
        {
            i += 1;
        }
    }
    printingUsingOne(number);
}

int main()
{
    int number = 0;
    printf("Enter the number: ");
    scanf("%d", &number);
    int combination[100] = {0};
    printf("All combinations of sums naturals:\n");
    combination[0] = number;
    newCombination(combination, number);
    return 0;
}

#include <stdio.h>

void newFractor(int maxDenominator, int firstNumerator, int firstDenominator, int secondNumerator, int secondDenominator)
{
    int newNumerator = firstNumerator + secondNumerator;
    int newDenominator = firstDenominator + secondDenominator;
    if (newDenominator <= maxDenominator) {
        newFractor(maxDenominator, firstNumerator, firstDenominator, newNumerator, newDenominator);
        printf("%d/%d ", newNumerator, newDenominator);
        newFractor(maxDenominator, newNumerator, newDenominator, secondNumerator, secondDenominator);
    }
}

void printingFractor(int number)
{
    printf("\n%d/%d ", 0, 1);
    newFractor(number, 0, 1, 1, 1);
    printf("%d/%d", 1, 1);
}

int main()
{
    int maxDenominator = 0;
    printf("Enter max denominator: ");
    scanf("%d", &maxDenominator);
    printingFractor(maxDenominator);
    return 0;
}

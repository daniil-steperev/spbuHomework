#include <stdio.h>
#include <stdbool.h>

bool gcd(int a, int b)
{
    while ((a > 0) && (b > 0))
        if (a > b)
        {
            a = a % b;
        }
        else
        {
            b = b % a;
        }
    return ((a == 1) || (b == 1));
}

 void countingAllFactors(int maxDenominator)
{
    int denominator = 1;
    int numerator = 0;
    while (numerator < maxDenominator)
   {
        while (denominator <= maxDenominator)
        {
            if (gcd(numerator, denominator) == true)
            {
                printf("%d/%d ", numerator, denominator);
            }
            denominator += 1;
        }
        numerator += 1;
        denominator = numerator + 1;
    }
    printf("%d/%d", numerator, numerator);
}

 int main()
{
    int maxDenominator = 0;
    printf("Enter max number of the denominator: ");
    scanf("%d", &maxDenominator);
    countingAllFactors(maxDenominator);
    return 0;
}

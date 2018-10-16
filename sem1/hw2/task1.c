#include <stdio.h>

int recursiveFibbonachi(int number)
{
    if (number == 0)
    {
        return 0;
    }
    else if (number == 1)
    {
        return 1;
    }
    else
    {
        return recursiveFibbonachi(number - 1) + recursiveFibbonachi(number - 2);
    }
}

int iterableFibbonachi(int number)
{
    int fibbonachi = 0;
    int fib1 = 1;
    int fib2 = 1;
    int counter = 2;
    while (counter < number)
    {
        fibbonachi = fib1 + fib2;
        fib1 = fib2;
        fib2 = fibbonachi;
        counter += 1;
    }
    return fibbonachi;
}

int main()
{
    int number = 0;
    printf("Enter number of Fibbonachi: ");
    scanf("%d", &number);
    printf("Recursive Fibbonachi is: ");
    printf("%d", recursiveFibbonachi(number));
    printf("\nIterable Fibbonachi is: ");
    printf("%d", iterableFibbonachi(number));
    return 0;
}

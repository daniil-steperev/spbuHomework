#include <stdio.h>

int recursiveFactorial(int n)
{
    if (n <= 1)
    {
        return 1;
    }
    else
    {
        return n * recursiveFactorial(n - 1);
    }
}

int iterativeFactorial(int n)
{
    int factorial = 1;
    for (int i = n; i > 1; i--)
    {
        factorial *= i;
    }
    return factorial;
}

int main()
{
    int n = 0;
    printf("Enter n: ");
    scanf("%d", &n);
    printf("Recursive factorial: %d", recursiveFactorial(n));
    printf("\nIterative factorial: %d", iterativeFactorial(n));
    return 0;
}

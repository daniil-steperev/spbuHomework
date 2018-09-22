#include <stdio.h>

int pow(int a, int n)
{
    if (n == 0)
    {
        return 1;
    }
    else
    {
        if (n % 2 == 0)
        {
            return pow(a * a, n / 2);
        }
        else
        {
            return a * pow(a, n - 1);
        }
    }
}

int main()
{
    int a = 0;
    printf("Enter a: ");
    scanf("%d", &a);
    int n = 0;
    printf("Enter n: ");
    scanf("%d", &n);
    printf("a^n = %d", pow(a, n));
    return 0;
}

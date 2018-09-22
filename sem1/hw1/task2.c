#include <stdio.h>

int searchIncompletePrivate(int a, int b)
{
    int n = 0;
    while (a - b >= 0)
    {
       a = a - b;
       n = n + 1;
    }
    return n;
}
int main()
{
    int a = 0;
    printf("Enter a: ");
    scanf("%d", &a);
    int b = 0;
    printf("Enter b: ");
    scanf("%d", &b);
    if (a >= 0 && b > 0)
    {
        printf("Incomplete private = %d", searchIncompletePrivate(a, b));
    }
    else if (b != 0)
    {
        printf("Incomplete private = %d", -searchIncompletePrivate(abs(a), abs(b)) -1);
    }
    else
    {
        printf("Incorrect solution: you entered b = 0");
    }
    return 0;
}

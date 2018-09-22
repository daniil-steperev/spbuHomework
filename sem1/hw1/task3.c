#include <stdio.h>

void print(char *array, int length)
{
    for (int i = 0; i < length; ++i)
    {
        printf("%c ", array[i]);
    }
}

void reverse(char *a, int m,  int n)
{
    for (int i = 0; i < m; ++i)
    {
        char change = a[m + n - i - 1];
        a[m + n - i - 1] = a[i];
        a[i] = change;
    }
    printf("\nReversed array: ");
    print(a, m + n);
}

int main()
{
    int m = 0;
    int n = 0;
    printf("Enter m: ");
    scanf("%d", &m);
    printf("Enter n: ");
    scanf("%d", &n);
    const length = m + n;
    char a[length] = {0};
    printf("Enter array without spaces: ");
    scanf("%s", a);
    printf("Entered array: ");
    print(a, m + n);
    reverse(a, m, n);
    return 0;
}

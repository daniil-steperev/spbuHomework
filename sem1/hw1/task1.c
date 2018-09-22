#include <stdio.h>

int main()
{
    int x = 0;
    printf("Enter x: ");
    scanf("%d", &x);
    int squaredX = x * x;
    printf("x^4 + x^3 + x^2 + x + 1 = ");
    printf("%d", (squaredX + 1) * (squaredX + x) + 1);
    return 0;
}

#include <stdio.h>

int pow(int number, int power)
{
    if (power == 0)
    {
        return 1;
    }
    else
    {
        if (power % 2 == 0)
        {
            return pow(number * number, power / 2);
        }
        else
        {
            return number * pow(number, power - 1);
        }
    }
}

int main()
{
    int number = 0;
    printf("Enter the number: ");
    scanf("%d", &number);
    int power = 0;
    printf("Enter the power: ");
    scanf("%d", &power);
    printf("%d ^ %d = %d", number, power, pow(number, power));
    return 0;
}

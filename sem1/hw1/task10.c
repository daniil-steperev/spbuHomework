#include <stdio.h>
#include <stdbool.h>

bool checkingPalindrome(char *string, int length)
{
    for (int i = 0; i < length / 2; ++i)
    {
        if (string[i] != string[length - 1 - i])
        {
            return 0;
        }
    }
    return 1;
}

int main()
{
    int length = 0;
    printf("Enter length of the string: ");
    scanf("%d", &length);
    printf("Enter the string: ");
    const lengthString = length;
    char string[lengthString + 100];
    scanf("%s", string);
    if (checkingPalindrome(string, length) == 1)
    {
        printf("The string is a palindrome");
    }
    else
    {
        printf("The string is not a palindrome");
    }
    return 0;
}

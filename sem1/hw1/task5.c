#include <stdio.h>

int checkingStaple(char correct, char previous)
{
    if (correct == previous)
    {
        return 1;
    }
    return 0;
}

int lastIndex(char *stack, int length)
{
    for (int i = 0; i < length; ++i)
    {
        if (stack[i] == 0)
        {
            return i;
        }
    }
    return length;
}

int checkingStapleBalance(char *string, int length)
{
    char stack[length];
    for (int i = 0; i < length; ++i)
    {
        stack[i] = 0;
        if ((string[i] == '(') || (string[i] == '[') || (string[i] == '{') || (string[i] == '<'))
                {
                    stack[lastIndex(stack, length)] = string[i];
                }
        else
        {
            char previous;
            previous = stack[lastIndex(stack, length) - 1];
            if (string[i] == ')')
            {
                if ((lastIndex(stack, length) == 0) || (checkingStaple('(', previous) == 0))
                {
                    return 0;
                }
                else
                {
                    stack[lastIndex(stack, length) - 1] = 0;
                }
            }
            else if (string[i] == ']')
            {
                if ((lastIndex(stack, length) == 0) || (checkingStaple('[', previous) == 0))
                {
                    return 0;
                }
                else
                {
                    stack[lastIndex(stack, length) - 1] = 0;
                }
            }
            else if (string[i] == '}')
            {
                if ((lastIndex(stack, length) == 0) || (checkingStaple('{', previous) == 0))
                {
                    return 0;
                }
                else
                {
                    stack[lastIndex(stack, length) - 1] = 0;
                }
            }
            else if (string[i] == '>')
            {
                if ((lastIndex(stack, length) == 0) || (checkingStaple('<', previous) == 0))
                {
                    return 0;
                }
                else
                {
                    stack[lastIndex(stack, length) - 1] = 0;
                }
            }
        }
    }
    if (lastIndex(stack, length) == 0)
    {
        return 1;
    }
    return 1;
}

int main()
{
    int length = 0;
    printf("Enter length of the string: ");
    scanf("%d", &length);
    printf("Enter the string: ");
    char string[length];
    scanf("%s", string);
    if (checkingStapleBalance(string, length) == 1)
    {
        printf("String is correct!");
    }
    else
    {
        printf("String is not correct!");
    }
    return 0;
}


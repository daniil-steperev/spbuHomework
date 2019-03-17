#include <stdio.h>

char *readLine(const len)
{
    char *str1;
    for (int i = 0; i < len; ++i)
    {
        scanf("%c", &str1[i]);
    }
    return *str1;
}

int main()
{
    char str[5];
    const len = 5;
    str = readLine(len);
    for (int i = 0; i < len; ++i)
    {
        printf("%c", str[i]);
    }
    return 0;
}

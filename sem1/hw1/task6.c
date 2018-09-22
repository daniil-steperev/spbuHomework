#include <stdio.h>

int countingNumberOfOccurences(char *string, int lenS, char *string1, int lenS1)
{
    int counter = 0;
    if (lenS < lenS1)
    {
        return 0;
    }
    else
    {
        for (int i = 0; i < lenS; i++)
        {
            if (string[i] == string1[0])
            {
                int j = 0;
                while ((string[i + j] == string1[j]) && (j < lenS1))
                {
                    j = j + 1;
                }
                if (j == lenS1)
                {
                    counter = counter + 1;
                }

            }
        }
    }
    return counter;
}

int main()
{
    int lenS;
    printf("Enter length s: ");
    scanf("%d", &lenS);
    int lenS1;
    printf("Enter length s1: ");
    scanf("%d", &lenS1);
    char string[lenS];
    printf("Enter string s: ");
    scanf("%s", string);
    char string1[lenS1];
    printf("Enter string s1: ");
    scanf("%s", string1);
    printf("s1 in s for %d times", countingNumberOfOccurences(string, lenS, string1, lenS1));
    return 0;
}

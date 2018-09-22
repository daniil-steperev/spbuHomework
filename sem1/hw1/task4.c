#include <stdio.h>

int main()
{
    int listOfCombinations[27];
    for (int i = 0; i < 27; ++i)
    {
        listOfCombinations[i] = 0;
    }
    for (int i = 0; i < 10; ++i)
    {
        for (int j = 0; j < 10; ++j)
        {
            for (int k = 0; k < 10; ++k)
            {
                listOfCombinations[i + j + k] += 1;
            }
        }
    }
    int summary = 0;
    for (int i = 0; i < 27; ++i)
    {
        listOfCombinations[i] = listOfCombinations[i] * listOfCombinations[i];
        summary += listOfCombinations[i];
    }
    printf("Sum of all happy tickets is %d", summary);
}

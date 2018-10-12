#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void print(int *array, int length)
{
    for (int i = 0; i < length; ++i)
    {
        printf("%d ", array[i]);
    }
}

void sortingArray(int *array, int length)
{
    for (int i = 1; i < length; i += 2)
    {
        int tmp = array[i];
        for (int j = i - 1; j >= 0; j -= 2)
        {
            if (array[j] < array[i])
            {
                break;
            }
            array[j+ 2] = array[j];
            array[j] = tmp;
        }
    }
    print(array, length);
}

int main()
{
    int length = 0;
    printf("Enter the length: ");
    scanf("%d", &length);
    int *array = new int[length] {0};
    printf("Do you want to enter the numbers? Yes/no.\n");
    char answer[3] = {0};
    scanf("%s", answer);
    if (answer[0] == 'Y' || answer[0] == 'y')
    {
        printf("Enter the numbers: ");
        for (int i = 0; i < length; ++i)
        {
            scanf("%d", &array[i]);
        }
    }
    else
    {
        for (int i = 0; i < length; ++i)
        {
            srand(time(0));
            int number = 11 + rand() % (33);
            array[i] = number;
        }
    }
    sortingArray(array, length);
    return 0;
    delete[] array;
}

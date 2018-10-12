#include <stdio.h>

void print(int *list, int length)
{
    for (int i = 0; i < length; ++i)
    {
        printf("%d ", list[i]);
    }
}

bool notTheBorder(int pointer, int max)
{
    return (pointer < max);
}

void assembleOneArray(int *listOfVasya, int quantityOfVasya, int *listOfPetr, int quantityOfPetr)
{
    int *newArray = new int[quantityOfVasya + quantityOfPetr] {0};
    int pointerVasya = 0;
    int pointerPetr = 0;
    int pointerNew = 0;
    while(pointerVasya < quantityOfVasya && pointerPetr < quantityOfPetr)
    {
        if ((notTheBorder(pointerVasya, quantityOfVasya)) && (listOfVasya[pointerVasya] >= listOfPetr[pointerPetr]))
        {
            newArray[pointerNew] = listOfVasya[pointerVasya];
            pointerVasya += 1;
            pointerNew += 1;
        }
        else
        {
            newArray[pointerNew] = listOfPetr[pointerPetr];
            pointerPetr += 1;
            pointerNew += 1;
        }
    }
    if (pointerNew != quantityOfPetr + quantityOfVasya - 1)
    {
        while (pointerVasya < quantityOfVasya && pointerNew != quantityOfPetr + quantityOfVasya - 1)
        {
            newArray[pointerNew] = listOfVasya[pointerVasya];
            pointerVasya += 1;
            pointerNew += 1;
        }
        while (pointerPetr < quantityOfPetr && pointerNew != quantityOfPetr + quantityOfVasya - 1)
        {
            newArray[pointerNew] = listOfPetr[pointerPetr];
            pointerPetr += 1;
            pointerNew += 1;
        }
    }
    printf("Sorted array: ");
    print(newArray, quantityOfVasya + quantityOfPetr - 1);
    delete[] newArray;
}

int main()
{
    int quantityOfPetr = 0;
    printf("Enter Petr's quantity of numbers: ");
    scanf("%d", &quantityOfPetr);
    int *listOfPetr = new int[quantityOfPetr] {0};
    printf("Enter Petr's numbers: ");
    for (int i = 0; i < quantityOfPetr; ++i)
    {
        scanf("%d", &listOfPetr[i]);
    }
    int quantityOfVasya = 0;
    printf("Enter Vasya's quantity of numbers: ");
    scanf("%d", &quantityOfVasya);
    printf("Enter Vasya's numbers: ");
    int *listOfVasya = new int[quantityOfVasya] {0};
    for (int i = 0; i < quantityOfVasya; ++i)
    {
        scanf("%d", &listOfVasya[i]);
    }
    assembleOneArray(listOfVasya, quantityOfVasya, listOfPetr, quantityOfPetr);
    delete[] listOfPetr;
    delete[] listOfVasya;
    return 0;
}

//The complexity is O(n).

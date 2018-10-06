#include <stdio.h>

void print(int *pyramid, int lengthOfPyramid)
{
    for (int i = 0; i < lengthOfPyramid; ++i)
    {
        printf("%d ", pyramid[i]);
    }
}

void siftingThroughAPyramid(int *pyramid, int numberOfRoot, int bottom)
{
    int numberOfMaxChild = 0;
    while (numberOfRoot * 2 <= bottom)
    {
        if (numberOfRoot * 2 == bottom)
        {
            numberOfMaxChild = numberOfRoot * 2;
        }
        else if (pyramid[numberOfRoot * 2] > pyramid[numberOfRoot * 2 + 1])
        {
            numberOfMaxChild = numberOfRoot * 2;
        }
        else
        {
            numberOfMaxChild = numberOfRoot * 2 + 1;
        }

        if (pyramid[numberOfRoot] < pyramid[numberOfMaxChild])
        {
            int tmp = pyramid[numberOfRoot];
            pyramid[numberOfRoot] = pyramid[numberOfMaxChild];
            pyramid[numberOfMaxChild] = tmp;
            numberOfRoot = numberOfMaxChild;
        }
        else
        {
            break;
        }
    }
}

void pyramidalSorting(int *pyramid, int lengthOfPyramid)
{
    for (int i = lengthOfPyramid / 2 - 1; i >= 0; --i)
    {
        siftingThroughAPyramid(pyramid, i, lengthOfPyramid - 1);
    }
    for (int i = lengthOfPyramid - 1; i >= 1; --i)
    {
        int tmp = pyramid[0];
        pyramid[0] = pyramid[i];
        pyramid[i] = tmp;
        siftingThroughAPyramid(pyramid, 0, i - 1);
    }
}

int main()
{
    printf("Enter the number of the pyramid: ");
    int lengthOfPyramid = 0;
    scanf("%d", &lengthOfPyramid);
    int pyramid[100] = {0};
    printf("\nEnter the numbers:");
    for (int i = 0; i < lengthOfPyramid; ++i)
    {
        scanf("%d", &pyramid[i]);
    }
    printf("\nNot sorted array: ");
    print(pyramid, lengthOfPyramid);
    pyramidalSorting(pyramid, lengthOfPyramid);
    printf("\nSorted array: ");
    print(pyramid, lengthOfPyramid);
    return 0;
}

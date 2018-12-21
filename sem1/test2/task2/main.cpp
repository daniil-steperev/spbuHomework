#include <iostream>

using namespace std;

void mergeSort(int *numbers, int leftElement, int rightElement, int sizeOfArray) {
    if (rightElement == leftElement)
        {
            return;
        }
    if (rightElement - leftElement == 1)
        {
            if (numbers[rightElement] < numbers[leftElement])
                {
                    swap(numbers[rightElement], numbers[leftElement]);
                }
            return;
    }
    int middleElement = (rightElement + leftElement) / 2;

    mergeSort(numbers, leftElement, middleElement, sizeOfArray);
    mergeSort(numbers, middleElement + 1, rightElement, sizeOfArray);

    int buf[sizeOfArray] {0};
    int xLeftElement = leftElement;
    int xRightElement = middleElement + 1;
    int current = 0;

    while (rightElement - leftElement + 1 != current) {
        if (xLeftElement > middleElement)
        {
            buf[current++] = numbers[xRightElement++];
        }
        else if (xRightElement > rightElement)
        {
            buf[current++] = numbers[xLeftElement++];
        }
        else if (numbers[xLeftElement] > numbers[xRightElement])
        {
            buf[current++] = numbers[xRightElement++];
        }
        else
        {
            buf[current++] = numbers[xLeftElement++];
        }
    }

    for (int i = 0; i < current; i++)
        {
            numbers[i + leftElement] = buf[i];
        }
}

void getArray(int *numbers, int sizeOfArray)
{
    for (int i = 0; i < sizeOfArray; i++)
    {
        cin >> numbers[i];
    }
}

void printArray(int *numbers, int sizeOfArray)
{
    for (int i = 0; i < sizeOfArray; i++)
    {
        cout << numbers[i] << ' ';
    }
    cout << endl;
}

int main()
{
    cout << "Enter size of array: ";
    int sizeOfArray = 0;
    cin >> sizeOfArray;

    int *numbers = new int [sizeOfArray] {0};
    cout << "Enter numbers: ";
    getArray(numbers, sizeOfArray);
    cout << endl;

    cout << "Entered array: ";
    printArray(numbers, sizeOfArray);

    cout << "Sorted array: ";
    mergeSort(numbers, 0, sizeOfArray - 1, sizeOfArray);
    printArray(numbers, sizeOfArray);

    delete[] numbers;
}

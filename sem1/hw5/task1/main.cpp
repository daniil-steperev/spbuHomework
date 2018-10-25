#include <iostream>

using namespace std;

void doShift(int **array, int &x, int &y, int shift, char direction)
{
    for (int k = 0; k < shift; k++)
    {
        cout << array[x][y] << " ";
        if (direction == 'r')
        {
            y++;
        }
        else if (direction == 'd')
        {
            x++;
        }
        else if (direction == 'l')
        {
            y--;
        }
        else
        {
            x--;
        }
    }
}

void print(int size)
{
    int **array = new int*[size];
    for (int i = 0; i < size; i++)
		array[i] = new int[size];

    cout << "Enter the array: ";
	for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
            cin >> array[i][j];
    }

    int shift = 1;
    int x = size / 2;
    int y = x;
    for (int i = 0; i < size / 2; i++)
    {
        doShift(array, x, y, shift, 'r');
        doShift(array, x, y, shift, 'd');
        shift++;
        doShift(array, x, y, shift, 'l');
        doShift(array, x, y, shift, 'u');
        shift++;
    }
    doShift(array, x, y, shift, 'r');

    for (int i = 0; i < size; i++)
        delete[] array[i];
    delete[] array;
}

int main()
{
    int size = 0;
    cout << "Enter size of the array: ";
    cin >> size;
    print(size);
    return 0;
}

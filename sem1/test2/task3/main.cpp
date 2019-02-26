#include <iostream>
#include <fstream>
#include "list.h"
using namespace std;

const int fileNameSize = 1000;

int main()
{
    char *fileName = new char[fileNameSize] {0};
    cout << "Input file name: ";
    cin >> fileName;
    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File can not be opened!";
        return 0;
    }

    List *numbers = createList();
    int number = 0;
    while (!file.eof())
    {
        file >> number;
        add(numbers, number);
    }

    if (isSymmetricList(numbers))
    {
        cout << "List is symmetric!" << endl;
    }
    else
    {
        cout << "List is not symmetric!" << endl;
    }

    file.close();
    deleteList(numbers);
    delete[] fileName;
    return 0;
}

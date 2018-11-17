#include <iostream>
#include <fstream>
#include <string.h>
#include "base.h"
#include "list.h"
using namespace std;

void saveBase(List *list)
{
    std::ofstream fNumbers("fNumbers.txt", std::ios::app);
    int number = sizeOfList(list);
    for (int i = 0; i < number; ++i)
    {
        int phone = 0;
        char name[256] = {'\0'};

        strcpy(name, get(list, i, phone));
        fNumbers << name << "   " << phone << endl;
    }
    fNumbers.close();
    clearList(list);
}

void downloadBase(List *list)
{
    ifstream fNumbers("fNumbers.txt");
    if (!fNumbers.is_open())
    {
        return;
    }
    char *name = new char[256] {'\0'};
    int phone = 0;
    fNumbers >> name;
    fNumbers >> phone;
    while (!fNumbers.eof())
    {
        addToList(list, name, phone);
        name = new char[256];
        fNumbers >> name;
        fNumbers >> phone;
    }
    delete[] name;
    fNumbers.close();
}

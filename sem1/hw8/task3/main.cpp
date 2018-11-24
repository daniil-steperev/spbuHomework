#include <iostream>
#include <fstream>
#include "hashTable.h"

using namespace std;

const int length = 5000;

int main()
{
    ifstream file("fileInput.txt");
    if (!file.is_open())
    {
        cout << "File can not be opened!";
        return 0;
    }

    char *word = new char[length];
    HashTable *table = createHashTable();
    while (!file.eof())
    {
        file >> word;
        MyString *newString = createString(word);
        addToTable(table, newString);
    }
    delete[] word;
	file.close();

    cout << "Load factor: " << loadFactor(table) << endl;
    cout << "Average chain length: " << returnChainLength(table) << endl;
    cout << "Max chain length: " << returnMaxChainLength(table) << endl;
    cout << "Number of different words in table: " << numberOfElements(table) << endl;
    cout << "Number of empty cells: " << emptyCells(table) << endl;

	deleteHashTable(table);
	return  0;
}

#include <iostream>
#include "list.h"
#include "myString.h"
#include "hashTable.h"

using namespace std;

const int size = 10009;

HashTable *createHashTable()
{
    HashTable *newTable = new HashTable;
    newTable->size = size;
    newTable->currentSize = 0;
    newTable->buckets = new List* [newTable->size];
    for (int i = 0; i < newTable->size; i++)
    {
        newTable->buckets[i] = createList();
    }
    return newTable;
}

int hashCode(HashTable *table, MyString *str)
{
    int answer = 0;
    int modulo = table->size;
    int length = countLength(str);
    for (int i = 0; i < length; i++)
    {
        answer = ((answer * 13) % modulo + str->content[i]) % modulo;
    }
    return answer;
}

void addToTable(HashTable *table, MyString *str)
{
    int hash = hashCode(table, str);
    add(table->buckets[hash], str);
    table->currentSize++;
}

void removeFromTable(HashTable *table, MyString *str)
{
    int hash = hashCode(table, str);
    removeListElement(table->buckets[hash], str);
    table->currentSize--;
}

double loadFactor(HashTable *table)
{
    return ((double)table->currentSize / (double)table->size);
}

double returnChainLength(HashTable *table)
{
    int answer = 0;
    for (int i = 0; i < table->size; i++)
    {
        answer += table->buckets[i]->size;
    }
    return (double) answer / table->currentSize;
}

void printChain(HashTable *table, int index)
{
    printList(table->buckets[index]);
}

int returnMaxChainLength(HashTable *table)
{
    int answer = 0;
    int index = 0;
    for (int i = 0; i < table->size; i++)
    {
        if (table->buckets[i]->size > answer)
        {
            answer = table->buckets[i]->size;
            index = i;
        }
    }
    cout << "Max chain length elements: ";
    printChain(table, index);
    return answer;
}

void deleteHashTable(HashTable *table)
{
    for (int i = 0; i < table->size; i++)
    {
        deleteList(table->buckets[i]);
    }

    deleteList(*table->buckets);
    delete table;
}

int numberOfElements(HashTable *table)
{
    return table->currentSize;
}

int emptyCells(HashTable *table)
{
    return table->size - table->currentSize;
}

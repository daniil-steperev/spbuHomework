#pragma once
#include "list.h"
#include "myString.h"

struct HashTable
{
    List **buckets;
    int size;
    int currentSize;
};

HashTable *createHashTable();

int hashCode(HashTable *table, MyString *str);

void addToTable(HashTable *table, MyString *str);
void removeFromTable(HashTable *table, MyString *str);

double loadFactor(HashTable *table);
double returnChainLength(HashTable *table);
int returnMaxChainLength(HashTable *table);
int emptyCells(HashTable *table);
int numberOfElements(HashTable *table);
void printChain(HashTable *table, int index);

void deleteHashTable(HashTable *table);

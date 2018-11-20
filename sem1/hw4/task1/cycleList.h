#pragma once

struct SortedCycleList;
SortedCycleList *createSortedCycleList();

void add(SortedCycleList *list, int value);
void addToEmptyList(SortedCycleList *list, int value);
void addFirst(SortedCycleList *list, int value);

void removeNext(SortedCycleList *current);
void removeElement(SortedCycleList *list, int value);
void removeFirst(SortedCycleList *list);
void removeByPosition(SortedCycleList *list);

void changePosition(SortedCycleList *list, int shift);

int getLast(SortedCycleList *list, int number);
void deleteCycleList(SortedCycleList *list);

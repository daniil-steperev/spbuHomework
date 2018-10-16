#pragma once

struct LoopedList;
LoopedList *createLoopedList();

void add(LoopedList *list, int value);
void addToEmptyList(LoopedList *list, int value);
void addFirst(LoopedList *list, int value);

void removeNext(LoopedList *current);
void removeElement(LoopedList *list, int value);
void removeFirst(LoopedList *list);
void removeByPosition(LoopedList *list);

void changePosition(LoopedList *list, int shift);

int getLast(LoopedList *list, int number);
void deleteLoopedList(LoopedList *list);

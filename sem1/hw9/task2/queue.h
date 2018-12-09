#pragma once
#include "binaryTree.h"

struct PriorityQueueElement
{
    BinaryTree *tree;
    PriorityQueueElement *next;
};

struct PriorityQueue
{
    PriorityQueueElement *first;
    int length;
};

PriorityQueue *createPriorityQueue();
void deletePriorityQueue(PriorityQueue *queue);

void push(PriorityQueue *queue, BinaryTree *tree);
BinaryTree *pop(PriorityQueue *queue);

int sizeOfQueue(PriorityQueue *queue);
bool isEmptyQueue(PriorityQueue *queue);

#include <iostream>
#include "queue.h"
#include "binaryTree.h"

PriorityQueue *createPriorityQueue()
{
    return new PriorityQueue{nullptr, 0};
}

void deletePriorityQueue(PriorityQueue *queue)
{
    PriorityQueueElement *current = queue->first;
    while (current)
    {
        PriorityQueueElement *deleteElement = current;
        current = current->next;
        deleteTree(deleteElement->tree);
        delete deleteElement;
    }
    delete queue;
}

void push(PriorityQueue *queue, BinaryTree *tree)
{
    queue->length++;
    PriorityQueueElement *newElement = new PriorityQueueElement;
    newElement->next = nullptr;
    newElement->tree = tree;

    if (isEmptyQueue(queue))
    {
        queue->first = newElement;
        return;
    }

    if (returnPriority(newElement->tree) <= returnPriority(queue->first->tree))
    {
        PriorityQueueElement *tmp = queue->first;
        queue->first = newElement;
        newElement->next = tmp;
        return;
    }
    PriorityQueueElement *current = queue->first;
    int elementPriority = returnPriority(tree);
    int currentPriority = returnPriority(current->tree);
    while ((current->next != nullptr) && (elementPriority > currentPriority))
    {
        current = current->next;
        currentPriority = returnPriority(current->tree);
    }
    newElement->next = current->next;
    current->next = newElement;
}

BinaryTree *pop(PriorityQueue *queue)
{
    queue->length--;
    if (isEmptyQueue(queue))
    {
        return nullptr;
    }

    BinaryTree *returnTree = queue->first->tree;

    PriorityQueueElement *nextElement = queue->first->next;
    delete queue->first;
    queue->first = nextElement;

    return returnTree;
}

bool isEmptyQueue(PriorityQueue *queue)
{
    return (queue->first == nullptr);
}

int sizeOfQueue(PriorityQueue *queue)
{
    return queue->length;
}

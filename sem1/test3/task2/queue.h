#pragma once

struct QueueElement
{
    int value;
    int priority;
    QueueElement *next;
    QueueElement *previous;
};

struct Queue
{
    QueueElement *head;
    QueueElement *tail;
    int size;
};

Queue *createQueue();
void deleteQueue(Queue *queue);

void enqueue(Queue *queue, int value, int priority);
int dequeue(Queue *queue);

bool isEmpty(Queue *queue);
int getSize(Queue *queue);

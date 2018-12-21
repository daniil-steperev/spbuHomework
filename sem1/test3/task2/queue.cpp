#include "queue.h"

Queue *createQueue()
{
    return new Queue{nullptr, nullptr, 0};
}

void deleteQueue(Queue *queue)
{
    QueueElement *current = queue->head;
    while (current)
    {
        QueueElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }

    delete queue;
}

bool isEmpty(Queue *queue)
{
    return (queue->head == nullptr);
}

void enqueue(Queue *queue, int value, int priority)
{
	queue->size++;

    QueueElement *newElement = new QueueElement;
    newElement->value = value;
    newElement->priority = priority;
    newElement->next = queue->head;
    newElement->previous = nullptr;

	while (newElement->next && (newElement->next->priority > priority))
	{
		newElement->previous = newElement->next;
		newElement->next = newElement->next->next;
	}

	if (newElement->previous)
	{
		newElement->previous->next = newElement;
	}
	else // newElement is queue->head
	{
		queue->head = newElement;
	}

	if (newElement->next)
	{
		newElement->next->previous = newElement;
	}
	else // newElement is queue->tail
	{
		queue->tail = newElement;
	}

    queue->size++;
}

int dequeue(Queue *queue)
{
    if (isEmpty(queue))
    {
        return -1;
    }

    QueueElement *deleteElement = queue->head;

    queue->head = queue->head->next;
    int value = deleteElement->value;

    delete deleteElement;
    queue->size--;

    return value;
}

int getSize(Queue *queue)
{
    return queue->size;
}

#include <stdio.h>
#include <limits.h>
#include "stack.h"

struct StackElement
{
    int value;
    StackElement *next;
};

struct Stack
{
    StackElement *first;
};

Stack *createStack()
{
    Stack *stack = new Stack {nullptr};
}

void push(Stack *stack, int value)
{
    stack->first = new StackElement {value, stack->first};
}

int pop(Stack *stack)
{
    if (isEmpty(stack))
    {
        return INT_MIN;
    }
    StackElement *deleteElement = stack->first;
    stack->first = stack->first->next;
    int deleteValue = deleteElement->value;
    delete deleteElement;
    return deleteValue;
}

bool isEmpty(Stack *stack)
{
    return stack->first == nullptr;
}

void deleteStack(Stack *stack)
{
    if (isEmpty(stack))
    {
        return;
    }
    StackElement *current = stack->first;
    while (current)
    {
        StackElement *deleteElement = current;
        current = current->next;
        delete deleteElement;
    }
    delete stack;
}

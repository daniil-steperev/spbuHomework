#include <iostream>
#include <limits.h>
#include "stack.h"

using namespace std;

struct StackElement
{
    int value;
    StackElement *next;
};

struct Stack
{
    StackElement *first;
    int size;
};

Stack *createStack()
{
    Stack *stack = new Stack {nullptr, 0};
}

void push(Stack *stack, int value)
{
    stack->first = new StackElement {value, stack->first};
    stack->size++;
}

int pop(Stack *stack)
{
    if (isEmpty(stack))
    {
        return 0;
    }
    StackElement *deleteElement = stack->first;
    stack->first = stack->first->next;
    int deleteValue = deleteElement->value;
    stack->size--;
    delete deleteElement;
    return deleteValue;
}

bool isEmpty(Stack *stack)
{
    return stack->first == nullptr;
}

void deleteStack(Stack *stack)
{
    while (stack->first != nullptr)
    {
        StackElement *deleteElement = stack->first;
        stack->first = deleteElement->next;
        delete deleteElement;
    }
    delete stack;
}

int sizeOfStack(Stack *stack)
{
    return stack->size;
}

void printStack(Stack *stack)
{
    if (stack->size == 0)
    {
        cout << "Stack is empty!";
        return;
    }
    StackElement *current = stack->first;
    for (int i = 0; i < stack->size; i++)
    {
        cout << current->value;
        current = current->next;
    }
}

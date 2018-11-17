#pragma once

struct Stack;

Stack *createStack();

void push(Stack *stack, int value);
int pop(Stack *stack);

bool isEmpty(Stack *stack);
void deleteStack(Stack *stack);

#pragma once
#include "stack.h"

int freeSpace(char *string);
bool isOperation(char operation);

void convertingFromStack(Stack *stack, char *postfixString);
void convertingFromBrackets(Stack *stack, char *postfixString);
void convertingToPostfix(char *infixString);

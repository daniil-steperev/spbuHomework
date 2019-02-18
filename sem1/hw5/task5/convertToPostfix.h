#pragma once
#include "stack.h"

int returnFreeSpace(char *string);
bool isOperation(char operation);

void convertFromStack(Stack *stack, char *postfixString);
void convertFromBrackets(Stack *stack, char *postfixString);
void convertToPostfix(char *infixString);

#pragma once

int freeSpace(char *string);
bool isOperation(char operation);

void convertingFromStack(Stack *stack, char *postfixString);
void convertingFromBrackets(Stack *stack, char *postfixString);

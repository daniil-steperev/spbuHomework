#pragma once
#include "stack.h"

void translateToBinary(Stack *stack, int number);

void printingInBinary(Stack *firstBinary, Stack *secondBinary);
void printingSummary(int *sumOfBinary, int size);

void summingBinary(Stack *firstBinary, Stack *secondBinary);
void summing(int firstNumber, int secondNumber);

int maxOfTwo(int first, int second);

void reverseStack(Stack *oldStack, Stack *reversedStack);

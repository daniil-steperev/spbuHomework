#pragma once

struct BinaryTree;

BinaryTree *createBinaryTree();

bool isEmpty(BinaryTree *tree);
bool findInTree(BinaryTree *tree, int element);

void addToTree(BinaryTree *tree, int element);

void removeFromTree(BinaryTree *tree, int element);

void printIncreasing(BinaryTree * tree);
void printDescending(BinaryTree *tree);
void printTree(BinaryTree *tree);

void deleteTree(BinaryTree *tree);

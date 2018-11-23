#pragma once
#include <fstream>
using namespace std;

struct BinaryTree;

BinaryTree *createBinaryTree();

bool isEmpty(BinaryTree *tree);
void findInTree(BinaryTree *tree, int element);

void addToTree(BinaryTree *tree, int element);

void removeFromTree(BinaryTree *tree, int element);

void printIncreasing(BinaryTree * tree);
void printDescending(BinaryTree *tree);
void printTree(BinaryTree *tree);

void deleteTree(BinaryTree *tree);

void writeToFile(ofstream &fileOut, BinaryTree *tree);

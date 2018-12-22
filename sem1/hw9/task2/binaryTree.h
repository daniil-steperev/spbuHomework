#pragma once
#include <fstream>
#include "list.h"

using namespace std;

struct BinaryTree;
struct Node;
struct List;

BinaryTree *createBinaryTree(int priority);
void deleteTree(BinaryTree *tree);

void addToTree(BinaryTree *tree, char element);
BinaryTree *mergeTrees(BinaryTree *first, BinaryTree *second);
void writeTree(ofstream &file, BinaryTree *tree);

int returnPriority(BinaryTree *tree);

bool isEmpty(BinaryTree *tree);
bool isLeaf(Node *son);

void assignCodes(ofstream &file, BinaryTree *tree, List *list);
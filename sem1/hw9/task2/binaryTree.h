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
void writeCodes(ofstream &file, BinaryTree *tree);

int returnPriority(BinaryTree *tree);
char returnNodeValue(Node *son);
MyString *returnNodeString(Node *son);

bool isEmpty(BinaryTree *tree);
bool isElement(ofstream &file, BinaryTree *tree, char destinition);

void assignCodes(ofstream &file, BinaryTree *tree);
void addElementsToList(List *list, BinaryTree *tree);

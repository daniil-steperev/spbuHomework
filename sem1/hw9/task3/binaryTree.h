#pragma once
#include <fstream>

using namespace std;

struct BinaryTree;

BinaryTree *createBinaryTree();

bool isEmpty(BinaryTree *tree);

void deleteTree(BinaryTree *tree);

void getTree(BinaryTree *tree, ifstream &file);
void translateMessage(BinaryTree *tree, char *coddedMessage, int length, ofstream &fileOut);

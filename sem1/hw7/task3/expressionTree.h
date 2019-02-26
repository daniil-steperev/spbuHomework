#pragma once
#include <fstream>

using namespace std;

struct Node
{
    int value;
    char operation;
    bool isDigit;
    Node *leftChild = nullptr;
    Node *rightChild = nullptr;
};

struct ExpressionTree
{
    Node *root;
};

ExpressionTree *createExpressionTree();

void scanTree(ifstream &fileInput, ExpressionTree *tree);

void printTree(ExpressionTree *tree);

int calculateTree(ExpressionTree *tree);

void deleteExpressionTree(ExpressionTree *tree);

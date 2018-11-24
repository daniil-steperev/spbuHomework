#pragma once

struct Node
{
    int value;
    int height;
    Node *leftChild;
    Node *rightChild;
};

struct AVLBinaryTree
{
    Node *root;
};

AVLBinaryTree *createAVLBinaryTree();

bool isEmpty(AVLBinaryTree *tree);
bool findInTree(AVLBinaryTree *tree, int element);
Node *createElement(int value);

void addToTree(AVLBinaryTree *tree, int element);

void removeFromTree(AVLBinaryTree *tree, int element);
void removeNode(Node *&son, int value);
Node *searchMinimumNode(Node* node);
void removeNode(Node *&son);


void printIncreasing(AVLBinaryTree * tree);
void printDescending(AVLBinaryTree *tree);
void printTree(AVLBinaryTree *tree);

void deleteTree(AVLBinaryTree *tree);

int height(Node *son);
int balanceFactor(Node *son);
void updateHeight(Node *son);
void rotateRight(Node *&son);
void rotateLeft(Node *&son);
void balance(Node *&son);

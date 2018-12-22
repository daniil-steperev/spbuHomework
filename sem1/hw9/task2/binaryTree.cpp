#include <iostream>
#include <fstream>
#include <string.h>
#include "binaryTree.h"
#include "myString.h"

using namespace std;

const int length = 256;
const int codeLength = 100;

struct Node
{
    char value;
    Node *leftChild;
    Node *rightChild;
};

struct BinaryTree
{
    Node *root;
    int priority;
};

BinaryTree *createBinaryTree(int priority)
{
    return new BinaryTree{nullptr, priority};
}

bool isLeaf(Node *son)
{
    return (son->leftChild == nullptr && son->rightChild == nullptr);
}

int returnPriority(BinaryTree *tree)
{
    return tree->priority;
}

bool isEmpty(BinaryTree *tree)
{
    return tree->root == nullptr;
}

void addToTree(Node *&son, char element)
{
    if (son == nullptr)
    {
        son = new Node {element, nullptr, nullptr};
    }
    if (element == son->value)
    {
        return;
    }
    if (element < son->value)
    {
        addToTree(son->leftChild, element);
    }
    else
    {
        addToTree(son->rightChild, element);
    }
}

void addToTree(BinaryTree *tree, char element)
{
    addToTree(tree->root, element);
}

BinaryTree *mergeTrees(BinaryTree *first, BinaryTree *second)
{
    int newPriority = first->priority + second->priority;
    BinaryTree *newTree = createBinaryTree(newPriority);

    Node *newNode = new Node {'*', nullptr, nullptr};
    newNode->leftChild = first->root;
    newNode->rightChild = second->root;
    newTree->root = newNode;

    delete first;
    delete second;

    return newTree;
}

void writeTree(ofstream &file, Node *son)
{
    if (isLeaf(son))
    {
        file << son->value;
        return;
    }

    file << "(* ";
    writeTree(file, son->leftChild);
    file << ' ';
    writeTree(file, son->rightChild);
    file << ')';
}

void writeTree(ofstream &file, BinaryTree *tree)
{
    if (!isEmpty(tree))
    {
        writeTree(file, tree->root);
        file << endl;
        return;
    }
    file << "(null)" << endl;
}

void deleteTree(Node *son)
{
    if (son != nullptr)
    {
        deleteTree(son->leftChild);
        deleteTree(son->rightChild);
        delete son;
    }
}

void deleteTree(BinaryTree *tree)
{
    if (!isEmpty(tree))
    {
        deleteTree(tree->root);
    }
    delete tree;
}

int findSpace(char *code)
{
    for (int i = 0; i < codeLength; i++)
    {
        if (code[i] == -1)
        {
            return i;
        }
    }

    return -1;
}

void assignCodes(ofstream &file, Node *son, MyString *previousCode, List *list)
{
    if (son->value != '*')
    {
        file << son->value << " - ";
        char *code = returnChar(previousCode);
        file << code << endl;
        delete[] code;
    }

    if (isLeaf(son))
    {
        addToList(list, son->value, previousCode);
        return;
    }

    if (son->leftChild != nullptr)
    {
        MyString *additiveCode = createString("0");
        MyString *firstCode = concatenate(previousCode, additiveCode);
        deleteString(additiveCode);

        assignCodes(file, son->leftChild, firstCode, list);
    }

    if (son->rightChild != nullptr)
    {
        MyString *additiveCode = createString("1");
        MyString *secondCode = concatenate(previousCode, additiveCode);
        deleteString(additiveCode);

        assignCodes(file, son->rightChild, secondCode, list);
    }

    deleteString(previousCode);
}

void assignCodes(ofstream &file, BinaryTree *tree, List *list)
{
    if (isEmpty(tree))
    {
        return;
    }

    if (tree->root->leftChild != nullptr)
    {
        MyString *previousCode = createString("0");
        assignCodes(file, tree->root->leftChild, previousCode, list);
    }

    if (tree->root->rightChild != nullptr)
    {
        MyString *previousCode = createString("1");
        assignCodes(file, tree->root->rightChild, previousCode, list);
    }
}

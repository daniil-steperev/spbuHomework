#include <iostream>
#include <string.h>
#include "binaryTree.h"
using namespace std;

const int length = 256;

struct Node
{
    char value;
    string code;
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
        string str;
        son = new Node {element, str, nullptr, nullptr};
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

    string str;
    Node *newNode = new Node {'*', str, nullptr, nullptr};
    newNode->leftChild = first->root;
    newNode->rightChild = second->root;
    newTree->root = newNode;

    return newTree;
}

void writeTree(ofstream &file, Node *son)
{
    file << "(";
    if (son->value == ' ')
    {
        file << "' '" << ' ';
    }
    else
    {
        file << son->value << ' ';
    }
    if (son->leftChild != nullptr) // printing node of left child
    {
        writeTree(file, son->leftChild);
    }
    else
    {
        file << "null";
    }

    file << " ";

    if (son->rightChild != nullptr) // printing node of right child
    {
        writeTree(file, son->rightChild);
    }
    else
    {
        file << "null";
    }
    file << ")";
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

bool isLeaf(Node *son)
{
    return (son->leftChild == nullptr && son->rightChild == nullptr);
}

void assignCodes(ofstream &file, Node *son, string previousCode)
{
    son->code = previousCode;
    if (son->value != '*')
    {
        if (son->value == ' ')
        {
            file << "' ' - ";
        }
        else
        {
            file << son->value << " - ";
        }

        file << son->code;
        file << endl;
    }

    if (isLeaf(son))
    {
        return;
    }

    if (son->leftChild != nullptr)
    {
        string additiveCode = "0";
        string newCode = previousCode + additiveCode;

        assignCodes(file, son->leftChild, newCode);
    }

    if (son->rightChild != nullptr)
    {
        string additiveCode = "1";
        string newCode = previousCode + additiveCode;

        assignCodes(file, son->rightChild, newCode);
    }

}

void assignCodes(ofstream &file, BinaryTree *tree)
{
    if (isEmpty(tree))
    {
        return;
    }

    if (tree->root->leftChild != nullptr)
    {
        string previousCode = "0";
        assignCodes(file, tree->root->leftChild, previousCode);

    }

    if (tree->root->rightChild != nullptr)
    {
        string previousCode = "1";
        assignCodes(file, tree->root->rightChild, previousCode);

    }
}


void addElementsToList(List *list, Node *son)
{
    if (son->value != '*')
    {
        addToList(list, son->value, son->code);
    }

    if (son->leftChild != nullptr)
    {
        addElementsToList(list, son->leftChild);
    }

    if (son->rightChild != nullptr)
    {
        addElementsToList(list, son->rightChild);
    }
}

void addElementsToList(List *list, BinaryTree *tree)
{
    addElementsToList(list, tree->root);
}

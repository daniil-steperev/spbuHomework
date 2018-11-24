#include <iostream>
#include <string.h>
#include "binaryTree.h"
using namespace std;

const int length = 256;

struct Node
{
    int value;
    Node *leftChild;
    Node *rightChild;
};

struct BinaryTree
{
    Node *root;
};

BinaryTree *createBinaryTree()
{
    return new BinaryTree{nullptr};
}

bool isEmpty(BinaryTree *tree)
{
    return tree->root == nullptr;
}

bool findInTree(Node *son, int element)
{
    if (son == nullptr)
    {
        return false;
    }
    if (son->value == element)
    {
        return true;
    }
    if (element < son->value)
    {
        findInTree(son->leftChild, element);
    }
    else
    {
        findInTree(son->rightChild, element);
    }
}

bool findInTree(BinaryTree *tree, int element)
{
    return findInTree(tree->root, element);
}

void addToTree(Node *&son, int element)
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
        return;
    }
    addToTree(son->rightChild, element);
}

void addToTree(BinaryTree *tree, int element)
{
    if (isEmpty(tree))
    {
        tree->root = new Node {element, nullptr, nullptr};
        return;
    }
    if (element == tree->root->value)
    {
        return;
    }
    if (element < tree->root->value)
    {
        addToTree(tree->root->leftChild, element);
        return;
    }
    addToTree(tree->root->rightChild, element);
}

void removeOrphan(Node *&son)
{
    delete son;
    son = nullptr;
}

void removeAloneNode(Node *&son)
{
    Node *deleteElement = son;
    if (son->rightChild != nullptr)
    {
        son = son->rightChild;
        delete deleteElement;
        return;
    }
    son = son->leftChild;
    delete deleteElement;
}

int numberOfChildren(Node *&son)
{
    if (son->leftChild == nullptr && son->rightChild == nullptr)
    {
        return 0;
    }
    else if (son->leftChild != nullptr && son->rightChild != nullptr)
    {
        return 2;
    }
    return 1;
}

Node *removeMinimal(Node *&son)
{
    if (son->leftChild == nullptr)
    {
        Node *current = son;
        delete son;
        return current;
    }
    else
    {
        removeMinimal(son->leftChild);
    }
}

void removeBoth(Node *&son)
{
    Node *newElement = removeMinimal(son->rightChild);
    Node *prevLeft = son->leftChild;
    Node *prevRight = son->rightChild;
    son = newElement;
    newElement->leftChild = prevLeft;
    newElement->rightChild = prevRight;
}

void removeFromTree(Node *&son, int element)
{
    if (son == nullptr)
    {
        cout << "There is no such element in tree!" << endl;
        return;
    }
    if (son->value == element)
    {
        if (numberOfChildren(son) == 0)
        {
            removeOrphan(son);
            return;
        }
        else if (numberOfChildren(son) == 1)
        {
            removeAloneNode(son);
            return;
        }
        else
        {
            removeBoth(son);
            return;
        }
    }
    if (element < son->value)
    {
        removeFromTree(son->leftChild, element);
        return;
    }
    removeFromTree(son->rightChild, element);
}

void removeFromTree(BinaryTree *tree, int element)
{
    removeFromTree(tree->root, element);
}

void printIncreasing(Node *son)
{
    if (son == nullptr)
    {
        return;
    }
    printIncreasing(son->leftChild);
    cout << son->value << " ";
    printIncreasing(son->rightChild);
}

void printIncreasing(BinaryTree * tree)
{
    if (tree->root == nullptr)
    {
        cout << "Tree is empty!" << endl;
        return;
    }
    cout << "Increasing print: ";
    printIncreasing(tree->root);
    cout << endl;
}

void printDescending(Node *son)
{
    if (son == nullptr)
    {
        return;
    }
    printDescending(son->rightChild);
    cout << son->value << " ";
    printDescending(son->leftChild);
}

void printDescending(BinaryTree *tree)
{
    if (tree->root == nullptr)
    {
        cout << "Tree is empty!" << endl;
        return;
    }
    cout << "Descending print: ";
    printDescending(tree->root);
    cout << endl;
}

void printTree(Node *son)
{
    cout << "(" << son->value << " "; // printing node of left child
    if (son->leftChild != nullptr)
    {
        printTree(son->leftChild);
    }
    else
    {
        cout << "null";
    }

    cout << " ";
    if (son->rightChild != nullptr) // printing node of right child
    {
        printTree(son->rightChild);
    }
    else
    {
        cout << "null";
    }
    cout << ")";
}

void printTree(BinaryTree *tree)
{
    if (!isEmpty(tree))
    {
        printTree(tree->root);
        cout << endl;
        return;
    }
    cout << "(null)" << endl;
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

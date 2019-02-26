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
    }
    else
    {
        addToTree(son->rightChild, element);
    }
}

void addToTree(BinaryTree *tree, int element)
{
    addToTree(tree->root, element);
}

void removeNode(Node *&son);

int removeRightest(Node *&son)
{
    if (son->rightChild != nullptr)
    {
        int answer = removeRightest(son->rightChild);
        return answer;
    }
    else
    {
        int answer = son->value;
        removeNode(son);
        return answer;
    }
}

void removeNode(Node *&son)
{
    if (son->leftChild == nullptr && son->rightChild == nullptr)
    {
        delete son;
        son = nullptr;
        return;
    }
    if (son->leftChild != nullptr && son->rightChild == nullptr)
    {
        Node *deleteNode = son;
        son = son->leftChild;
        delete deleteNode;
        return;
    }
    if (son->leftChild == nullptr && son->rightChild != nullptr)
    {
        Node *deleteNode = son;
        son = son->rightChild;
        delete deleteNode;
        return;
    }
    if (son->leftChild != nullptr && son->rightChild != nullptr)
    {
        son->value = removeRightest(son->leftChild);
    }
}

void removeFromTree(Node *&son, int element)
{
    if (son == nullptr)
    {
        cout << "There is not such element in tree!" << endl;
        return;
    }
    if (son->value == element)
    {
        removeNode(son);
        return;
    }
    if (son->value > element)
    {
        removeFromTree(son->leftChild, element);
    }
    else
    {
        removeFromTree(son->rightChild, element);
    }
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

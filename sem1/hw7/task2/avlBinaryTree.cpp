#include <iostream>
#include <string.h>
#include "avlBinaryTree.h"
using namespace std;

AVLBinaryTree *createAVLBinaryTree()
{
    return new AVLBinaryTree{nullptr};
}

bool isEmpty(AVLBinaryTree *tree)
{
    return tree->root == nullptr;
}

int height(Node *son)
{
    return son ? son->height : 0;
}

int balanceFactor(Node *son)
{
    return height(son->rightChild) - height(son->leftChild);
}

void updateHeight(Node *son)
{
    int heightLeft = height(son->leftChild);
    int heightRight = height(son->rightChild);
    if (heightLeft > heightRight)
    son->height  = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
}

void rotateRight(Node *&son)
{
    Node *pivot = son->leftChild;
    son->leftChild = pivot->rightChild;
    pivot->rightChild = son;

    updateHeight(son);
    updateHeight(pivot);
    son = pivot;
}

void rotateLeft(Node *&son)
{
    Node *pivot = son->rightChild;
    son->rightChild = pivot->leftChild;
    pivot->rightChild = son;

    updateHeight(pivot);
    updateHeight(son);
    son = pivot;
}

void balance(Node *&son)
{
    updateHeight(son);

    if (balanceFactor(son) == 2)
    {
        if (balanceFactor(son->rightChild) < 0)
        {
            rotateRight(son->rightChild);
        }
        rotateLeft(son);
    }
    if (balanceFactor(son) == -2)
    {
        if (balanceFactor(son->leftChild) > 0)
        {
            rotateLeft(son->leftChild);
        }
        rotateRight(son);
    }
}

void findInTree(Node *son, int element)
{
    if (son == nullptr)
    {
        cout << "Not found!" << endl;
        return;
    }
    if (son->value == element)
    {
        cout << "Element in tree!" << endl;
        return;
    }
    if (element < son->value)
    {
        findInTree(son->leftChild, element);
        return;
    }
    findInTree(son->rightChild, element);
}

void findInTree(AVLBinaryTree *tree, int element)
{
    if (isEmpty(tree))
    {
        cout << "Not found!" << endl;
    }
    if (element < tree->root->value)
    {
        findInTree(tree->root->leftChild, element);
    }
    else if (element > tree->root->value)
    {
        findInTree(tree->root->rightChild, element);
    }
}

void addToTree(Node *&son, int element)
{
    if (son == nullptr)
    {
        son = new Node {element, 1, nullptr, nullptr};
        balance(son);
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

    balance(son);
}

void addToTree(AVLBinaryTree *tree, int element)
{
    addToTree(tree->root, element);
}


void removeNode(Node *&son, int value)
{
	if (son == nullptr)
	{
		return;
	}
	if (son->value == value)
	{
		removeNode(son);
		return;
	}
 	if (son->value > value)
	{
		removeNode(son->leftChild, value);
	}
	else
	{
		removeNode(son->rightChild, value);
	}
}


Node *searchMinimumNode(Node* node)
{
	if (node->leftChild != nullptr)
	{
		return searchMinimumNode(node->leftChild);
	}
	else
	{
		return node;
		cout << node->value;
	}
}

void removeNode(Node *&son)
{
	if ((son->leftChild == nullptr) && (son->rightChild == nullptr))
	{
		delete son;
		son = nullptr;
		return;
	}
 	if ((son->leftChild != nullptr) && (son->rightChild == nullptr))
	{
		Node *toDelete = son;
		son = son->leftChild;
		delete toDelete;
		return;
	}
 	if ((son->leftChild == nullptr) && (son->rightChild != nullptr))
	{
		Node *toDelete = son;
		son = son->rightChild;
		delete toDelete;
		return;
	}
 	if ((son->leftChild != nullptr) && (son->rightChild != nullptr))
	{
		Node **temp = &son->rightChild;
		searchMinimumNode(*temp);
		son->value = (*temp)->value;
		removeNode(*temp);
		updateHeight(son);
		balance(son);
		return;
	}
}

void removeFromTree(AVLBinaryTree *tree, int element)
{
    if (isEmpty(tree))
    {
        return;
    }
    if (element == tree->root->value)
    {
        cout << "You can not delete tree root!" << endl;
    }
    if (element < tree->root->value)
    {
        removeNode(tree->root->leftChild, element);
        return;
    }
    removeNode(tree->root->rightChild, element);
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

void printIncreasing(AVLBinaryTree * tree)
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

void printDescending(AVLBinaryTree *tree)
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

void printTree(AVLBinaryTree *tree)
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

void deleteTree(AVLBinaryTree *tree)
{
    if (!isEmpty(tree))
    {
        deleteTree(tree->root);
    }
    delete tree;
}

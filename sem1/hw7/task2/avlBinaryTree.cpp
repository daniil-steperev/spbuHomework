#include <iostream>
#include <string.h>
#include "avlBinaryTree.h"
using namespace std;

AVLBinaryTree *createAVLBinaryTree()
{
    return new AVLBinaryTree{nullptr};
}

Node *createElement(int value)
{
    Node *newElement = new Node;
    newElement->height = 1;
    newElement->leftChild = nullptr;
    newElement->rightChild = nullptr;
    newElement->value = value;
    return newElement;
}

bool isEmpty(AVLBinaryTree *tree)
{
    return tree->root == nullptr;
}

int height(Node *son)
{
    if (son != nullptr)
    {
        return son->height;
    }
    return 0;
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
    {
        son->height = heightLeft + 1;
    }
    else
    {
        son->height = heightRight + 1;
    }
}

void rotateRight(Node *&son)
{
    Node *original = son;
	Node *temp = original->leftChild;
	original->leftChild = temp->rightChild;
	temp->rightChild = original;
	updateHeight(original);
	updateHeight(temp);
	son = temp;
}

void rotateLeft(Node *&son)
{
    Node *original = son;
	Node *temp = original->rightChild;
	original->rightChild = temp->leftChild;
	temp->leftChild = original;
	updateHeight(original);
	updateHeight(temp);
	son = temp;
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
		return;
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

bool findInTree(AVLBinaryTree *tree, int element)
{
    return findInTree(tree->root, element);
}

void addToTree(Node *&son, int element)
{
    if (son == nullptr)
    {
        son = createElement(element);
        return;
    }
    if (element == son->value)
    {
        return;
    }
    if (son->value > element)
    {
        addToTree(son->leftChild, element);
    }
    else
    {
        addToTree(son->rightChild, element);
    }

    updateHeight(son);
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

int removeRightest(Node *&son)
{
	if (son->rightChild != nullptr)
	{
		int answer = removeRightest(son->rightChild);
		updateHeight(son);
		balance(son);
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
	if ((son->leftChild == nullptr) && (son->rightChild == nullptr))
	{
		delete son;
		son = nullptr;
		return;
	}
 	if ((son->leftChild != nullptr) && (son->rightChild == nullptr))
	{
		Node *deleteSon = son;
		son = son->leftChild;
		delete deleteSon;
		return;
	}
 	if ((son->leftChild == nullptr) && (son->rightChild != nullptr))
	{
		Node *deleteSon = son;
		son = son->rightChild;
		delete deleteSon;
		return;
	}
 	if ((son->leftChild != nullptr) && (son->rightChild != nullptr))
	{
		son->value = removeRightest(son->leftChild);
        updateHeight(son);
        balance(son);
	}
}

void removeFromTree(Node *&son, int element)
{
    if (son == nullptr)
    {
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

    updateHeight(son);
    balance(son);
}

void removeFromTree(AVLBinaryTree *tree, int element)
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

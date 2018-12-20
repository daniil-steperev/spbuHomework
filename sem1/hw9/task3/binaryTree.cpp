#include <iostream>
#include <fstream>
#include "binaryTree.h"
using namespace std;

const int length = 256;

struct Node
{
    char value;
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

void deleteTree(Node *son)
{
	if (son->leftChild)
	{
		deleteTree(son->leftChild);
	}
	if (son->rightChild)
	{
		deleteTree(son->rightChild);
	}

	delete son;
}

void deleteTree(BinaryTree *tree)
{
    if (!isEmpty(tree))
    {
        deleteTree(tree->root);
    }
    delete tree;
}

Node *getNode(ifstream &file)
{
    BinaryTree *intermediateTree = createBinaryTree();
	intermediateTree->root = new Node{ '*', nullptr, nullptr };

    file.get(); // get space
    file.get(); // get bracket

    char currentElement = '\0';
    file.get(currentElement);
    if (currentElement == '(') // case left tree branch
    {
        intermediateTree->root->leftChild = getNode(file);
    }
    else // case element
    {
        intermediateTree->root->leftChild = new Node {currentElement, nullptr, nullptr};
    }

    file.get(); // get space
    file.get(currentElement); // get new element
    if (currentElement == '(') // case right tree branch
    {
        intermediateTree->root->rightChild = getNode(file);
    }
    else
    {
        intermediateTree->root->rightChild = new Node {currentElement, nullptr, nullptr};
    }

    file.get(); // get space

    Node *newNode = intermediateTree->root;
    delete intermediateTree; // we should delete only pointer as we want to use our tree
    return newNode;
}

void getTree(BinaryTree *tree, ifstream &file)
{
    file.get();

    tree->root = getNode(file);
}

bool isLeaf(Node *node)
{
    return (node->leftChild == nullptr && node->rightChild == nullptr);
}

void translateMessage(BinaryTree *tree, char *coddedMessage, int length, ofstream &fileOut)
{
    int i = 0;
    Node *currentNode = tree->root;
    while (i < length)
    {
        if (coddedMessage[i] == '0')
        {
            currentNode = currentNode->leftChild;
        }
        else if (coddedMessage[i] == '1')
        {
            currentNode = currentNode->rightChild;
        }
        else
        {
            cout << "Codded message is not correct!";
            return;
        }

        if (isLeaf(currentNode))
        {
            fileOut << currentNode->value;
            currentNode = tree->root;
        }
        i++;
    }
}

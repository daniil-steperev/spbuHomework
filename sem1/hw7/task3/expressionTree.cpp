#include <iostream>
#include <fstream>
#include "expressionTree.h"

using namespace std;

ExpressionTree *createExpressionTree()
{
    return new ExpressionTree{nullptr};
}

int makeDigit(char symbol)
{
    return symbol - '0';
}

bool is_Digit(char symbol)
{
    return ('0' <= symbol && symbol <= 9);
}

Node *scanTree(ifstream &fileInput)
{
    char symbol = fileInput.get();

    Node *newElement = new Node;

    if (symbol == '(')
    {
        symbol = fileInput.get();

        if (symbol == ' ') // as in the example
        {
            symbol = '*';
        }
        else
            {
                fileInput.get(); // get operation
            }

        newElement->isDigit = false;
        newElement->operation = symbol;
        newElement->leftChild = scanTree(fileInput);
        fileInput.get();
        newElement->rightChild = scanTree(fileInput);
        fileInput.get();
    }
    else
    {
        int number = makeDigit(symbol);
        symbol = fileInput.get();
        while (is_Digit(symbol))
        {
            number = number * 10 + makeDigit(symbol);
            symbol = fileInput.get();
        }
        fileInput.unget();
        newElement->isDigit = true;
        newElement->leftChild = nullptr;
        newElement->rightChild = nullptr;
        newElement->value = number;
    }

    return newElement;
}

void scanTree(ifstream &fileInput, ExpressionTree *tree)
{
    tree->root = scanTree(fileInput);
}

void printTree(Node *node)
{
    if (node->isDigit)
    {
        cout << node->value;
        return;
    }

    cout << '(';
    printTree(node->leftChild);
    cout << ' ' << node->operation << ' ';
    printTree(node->rightChild);
    cout << ')';
}

void printTree(ExpressionTree *tree)
{
    printTree(tree->root);
}

int calculateTree(Node *node)
{
    if (node->isDigit) // digit
    {
        return node->value;
    }

    char operation = node->operation; // operation
    if (operation == '+')
    {
        return calculateTree(node->leftChild) + calculateTree(node->rightChild);
    }
    else if (operation == '-')
    {
        return calculateTree(node->leftChild) - calculateTree(node->rightChild);
    }
    else if (operation == '/')
    {
        return calculateTree(node->leftChild) / calculateTree(node->rightChild);
    }
    else if (operation == '*')
    {
        return calculateTree(node->leftChild) * calculateTree(node->rightChild);
    }
}

int calculateTree(ExpressionTree *tree)
{
    return calculateTree(tree->root);
}

void deleteExpressionTree(Node *&node)
{
	if (node == nullptr)
	{
		return;
	}
	deleteExpressionTree(node->rightChild);
	deleteExpressionTree(node->leftChild);
	delete node;
}

void deleteExpressionTree(ExpressionTree *tree)
{
    deleteExpressionTree(tree->root);
	delete tree;
}

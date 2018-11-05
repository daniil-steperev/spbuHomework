#include <iostream>
#include "binaryTree.h"

using namespace std;

void addElement(BinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    addToTree(tree, element);
}

void removeElement(BinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    removeFromTree(tree, element);
}

void findTree(BinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    findInTree(tree, element);
    cout << "\n";
}

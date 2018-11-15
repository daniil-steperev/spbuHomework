#include <iostream>
#include "avlBinaryTree.h"

using namespace std;

void addElement(AVLBinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    addToTree(tree, element);
}

void removeElement(AVLBinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    removeFromTree(tree, element);
}

void findTree(AVLBinaryTree *tree)
{
    cout << "Enter element: ";
    int element = 0;
    cin >> element;
    cout << endl;
    findInTree(tree, element);
    cout << "\n";
}

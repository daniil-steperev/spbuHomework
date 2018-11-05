#include <iostream>
#include "binaryTree.h"

using namespace std;

void test()
{
    BinaryTree *tree = createBinaryTree();

    addToTree(tree, 5);
    addToTree(tree, 2);
    addToTree(tree, 10);
    addToTree(tree, 12);

    printIncreasing(tree);
    printDescending(tree);
    printTree(tree);

    cout << "Find in tree 10: \n";
    findInTree(tree, 10);

    cout << "Remove 2: \n";
    removeFromTree(tree, 2);
    printTree(tree);

    deleteTree(tree);
}

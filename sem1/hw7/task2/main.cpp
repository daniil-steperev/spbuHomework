#include <iostream>
#include "avlBinaryTree.h"

using namespace std;

void menu()
{
    cout << "0 - exit" << endl;
    cout << "1 - add element" << endl;
    cout << "2 - remove element" << endl;
    cout << "3 - print increasing" << endl;
    cout << "4 - print descending" << endl;
    cout << "5 - print tree" << endl;
    cout << "6 - find in tree" << endl;
}

int main()
{
    AVLBinaryTree *tree = createAVLBinaryTree();

    int task = -1;
    while (task != 0)
    {
        menu();
        cout << "Enter your task: ";
        cin >> task;
        cout << endl;
        if (task == 1)
        {
            int element = 0;
            cout << "Element to add: ";
            cin >> element;
            addToTree(tree, element);
        }
        else if (task == 2)
        {
            int element = 0;
            cout << "Element to delete: ";
            cin >> element;
            removeFromTree(tree, element);
        }
        else if (task == 3)
        {
            printIncreasing(tree);
        }
        else if (task == 4)
        {
            printDescending(tree);
        }
        else if (task == 5)
        {
            printTree(tree);
        }
        else if (task == 6)
        {
            int element = 0;
            cout << "Element to find: ";
            cin >> element;
            if (findInTree(tree, element))
            {
                cout << "Element " << element << " in tree!" << endl;
            }
            else
            {
                cout << "Element " << element << " not in tree!" << endl;
            }
        }
        else if (task == 0)
        {
            cout << "Thanks for using my program!";
        }
    }

    deleteTree(tree);
    return 0;
}

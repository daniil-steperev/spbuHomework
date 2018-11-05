#include <iostream>
#include "binaryTree.h"
#include "test.h"
#include "operations.h"

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
    BinaryTree *tree = createBinaryTree();
    char answer[3] = {0};
    cout << "Do you want to run a test? y/n ";
    cin >> answer;
    if (answer[0] == 'y' || answer[0] == 'Y')
    {
        test();
        cout << "\n";
    }

    int task = -1;
    while (task != 0)
    {
        menu();
        cout << "Enter your task: ";
        cin >> task;
        cout << endl;
        if (task == 1)
        {
            addElement(tree);
        }
        else if (task == 2)
        {
            removeElement(tree);
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
            findTree(tree);
        }
        else if (task == 0)
        {
            cout << "Thanks for using my program!";
        }
    }

    deleteTree(tree);
    return 0;
}

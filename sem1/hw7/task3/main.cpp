#include <iostream>
#include <fstream>
#include "expressionTree.h"

using namespace std;

int main()
{
    ifstream fileInput("fileInput.txt");
    if (!fileInput.is_open())
    {
        cout << "File not found.";
        return 0;
    }

    ExpressionTree *tree = createExpressionTree();
    scanTree(fileInput, tree);
    cout << "Expression tree: ";
    printTree(tree);
    cout << endl;

    cout << "Result: " << calculateTree(tree) << endl;

    fileInput.close();
    deleteExpressionTree(tree);
    return 0;
}

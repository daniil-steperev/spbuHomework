#include <iostream>
#include <fstream>
#include <string.h>
#include "stack.h"
#include "binaryTree.h"
#include <limits.h>

using namespace std;
const int sizeOfString = 1000;

bool isDigit(char element)
{
    return ('0' <= element && element <= '9');
}

int makeDigit(char element)
{
    return element - '0';
}

void processString(BinaryTree *tree, char* string)
{
    int length = strlen(string);
    Stack *stack = createStack();
    for (int i = 0; i < length; i++)
    {
        char element = string[i];
        if (isDigit(element))
        {
            int number = makeDigit(element);
            push(stack, number);
        }
        else
        {
            int number = 0;
            int digit = 0;
            int degree = 1;
            while (digit != INT_MIN)
            {
                digit = pop(stack);
                if (digit != INT_MIN)
                    {
                        number += digit * degree;
                        degree *= 10;
                    }
            }
            addToTree(tree, number);
        }
    }
    deleteStack(stack);
}

int main()
{
    char *string = new char[sizeOfString] {'\0'};
    BinaryTree *tree = createBinaryTree();
    int a = 0;
    cout << "Enter a: ";
    cin >> a;
    cout << endl;
    int b = 0;
    cout << "Enter b: ";
    cin >> b;
    cout << endl;

    addToTree(tree, a);
    addToTree(tree, b);

    ifstream fileIn("file.txt");
    ofstream fileOut("fileOut.txt");
    if (!fileIn.is_open())
    {
        cout << "File can not be opened!";
    }
    else
    {
        while (!fileIn.eof())
        {
            fileIn.getline(string, sizeOfString);
            processString(tree, string);
        }
    }

    writeToFile(fileOut, tree);

    delete[] string;
    deleteTree(tree);
}

#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;
const int length = 256;

bool isEmptySymbol(char symbol)
{
    return ((symbol == ' ') || (symbol == '\t') || (symbol == '\n'));
}

bool checkingForEmpty(char *line)
{
    int position = strlen(line) - 1;
    while ((position >= 0) && isEmptySymbol(line[position]))
    {
        position -= 1;
    }
    return position != -1;
}

 int main()
{
    ifstream file("file.txt");
    if (!file.fail())
    {
        char line[length] {0};
        int counter = 0;
        while (!file.eof())
        {
            file.getline(line, length);
            if (!checkingForEmpty(line))
            {
                counter++;
            }
        }
        cout << "Quantity of not empty strings: " << counter;
    }
    else
    {
        cout << "Can not open file :(";
    }
    return 0;
}

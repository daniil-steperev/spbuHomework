#include <iostream>
#include "expression.h"

using namespace std;

int main()
{
    int maxDegree = 0;
    cout << "Enter max degree of polynom: ";
    cin >> maxDegree;
    cout << endl;

    int *coefficients = new int[maxDegree] {0};
    cout << "Enter coefficients of polynom: ";
    for (int i = 0; i < maxDegree; i++)
    {
        cin >> coefficients[i];
    }
    fillStrings(maxDegree, coefficients);
    delete[] coefficients;
}

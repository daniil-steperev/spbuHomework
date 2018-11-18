#include <iostream>
#include "expression.h"

using namespace std;

int main()
{
    int maxDegree = 0;
    cout << "Enter max degree of polynom: ";
    cin >> maxDegree;
    cout << endl;

    int *coefficients = new int[maxDegree + 1] {0};
    cout << "Enter coefficients of polynom: ";
    for (int i = 0; i < maxDegree + 1; i++)
    {
        cin >> coefficients[i];
    }
    fillStrings(maxDegree + 1, coefficients);
    delete[] coefficients;
}

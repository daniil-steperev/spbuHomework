#include <iostream>
#include "expression.h"

using namespace std;

const int maxSize = 256;

void print(char *degree, char *polynom)
{
    cout << "Your expression: " << endl;
    for (int i = 0; i < maxSize; i++)
    {
        cout << degree[i];
    }
    cout << "\n";
    for (int i = 0; i < maxSize; i++)
    {
        cout << polynom[i];
    }
}

int abs(int number)
{
    if (number < 0)
    {
        return -number;
    }
    return number;
}

int lengthOfNumber(int number)
{
    int length = 0;
    if (number < 0)
    {
        number = -number;
    }

    while (number > 0)
    {
        length++;
        number /= 10;
    }
    return length;
}

int pow(int number, int degree)
{
    if (degree == 0)
    {
        return 1;
    }
    if (degree == 1)
    {
        return number;
    }
    if (degree % 2 == 0)
    {
        return pow(number * number, degree / 2);
    }
    else
    {
        return number * pow(number, degree - 1);
    }
}

void fillStrings(int maxDegree, int *coefficients)
{
    char *polynom = new char[maxSize] {0};
    char *degree = new char[maxSize] {0};
    int polynomIndex = 0;
    int degreeIndex = 0;
    int currentDegree = 0;
    for (int i = 0; i < maxDegree; i++)
    {
        currentDegree = maxDegree - i;
        if (coefficients[i] != 0)
        {
            if (coefficients[i] > 0) // filling the sign!
            {
                polynom[polynomIndex] = '+';
                polynomIndex += 2;
            }
            else
            {
                polynom[polynomIndex] = '-';
                polynomIndex += 2;
            }
            degreeIndex += 2;

            coefficients[i] = abs(coefficients[i]);
            if (coefficients[i] != 1)
            {
                int length = lengthOfNumber(coefficients[i]);
                for (int j = 1; j <= length; j++)
                {
                    polynom[polynomIndex] = (coefficients[i] / pow(10, length - j)) % 10 + '0'; // take only one digit
                    polynomIndex++;
                    degreeIndex++;
                }
                polynom[polynomIndex] = 'x';
                polynomIndex++;
                degreeIndex++;

                length = lengthOfNumber(currentDegree);
                for (int j = 1; j <= length; j++)
                {
                    degree[degreeIndex] = (currentDegree / pow(10, length - j)) % 10 + '0'; // take only one digit
                    degreeIndex++;
                    polynomIndex++;
                }
            }
            else
            {
                polynom[polynomIndex] = 'x';
                polynomIndex++;
                degreeIndex++;

                int length = lengthOfNumber(currentDegree);
                for (int j = 1; j <= length; j++)
                {
                    degree[degreeIndex] = (currentDegree / pow(10, length - j)) % 10 + '0'; // take only one digit
                    degreeIndex++;
                    polynomIndex++;
                }
            }
        }
    }
    print(degree, polynom);
    delete[] polynom;
    delete[] degree;
}

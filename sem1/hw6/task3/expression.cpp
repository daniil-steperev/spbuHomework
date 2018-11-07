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
    cout << endl;
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

int returnFirst(int number)
{
    int digit = 0;
    if (number < 0)
    {
        number = -number;
    }
    while (number)
    {
        digit = number % 10;
        number /= 10;
    }
    return digit;
}

int removeFirst(int &number)
{
    int length = lengthOfNumber(number);

    if (number < 0)
    {
        int first = returnFirst(-number);
        int rounded = first * pow(10, length - 1);
        number += rounded;
        return number;
    }
    int first = returnFirst(number);
    int rounded = first * pow(10, length - 1);
    number -= rounded;
    return number;
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
        currentDegree = maxDegree - i - 1;
        if (coefficients[i] != 0 && currentDegree != 0)
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
                    polynom[polynomIndex] = (char)(returnFirst(coefficients[i]) + '0'); // take only one digit
                    coefficients[i] = removeFirst(coefficients[i]);
                    polynomIndex++;
                    degreeIndex++;
                }
                polynom[polynomIndex] = 'x';
                polynomIndex++;
                degreeIndex++;

                if (currentDegree > 1)
                {
                    length = lengthOfNumber(currentDegree);
                    for (int j = 1; j <= length; j++)
                    {
                        degree[degreeIndex] = (char)(returnFirst(currentDegree) + '0'); // take only one digit
                        currentDegree = removeFirst(currentDegree);
                        degreeIndex++;
                        polynomIndex++;
                    }
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
                    degree[degreeIndex] = returnFirst(currentDegree) + '0'; // take only one digit;
                    removeFirst(currentDegree);
                    degreeIndex++;
                    polynomIndex++;
                }
            }
        }
        else if (currentDegree == 0 && coefficients[i] != 0)
        {
            polynomIndex++;
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

            int length = lengthOfNumber(coefficients[i]);
            for (int j = 1; j <= length; j++)
                {
                    polynom[polynomIndex] = (coefficients[i] / pow(10, length - j)) % 10 + '0'; // take only one digit
                    polynomIndex++;
                }
        }
    }
    print(degree, polynom);
    delete[] polynom;
    delete[] degree;
}

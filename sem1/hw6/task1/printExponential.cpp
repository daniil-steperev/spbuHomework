#include <iostream>
#include <iomanip>
#include "printExponential.h"

using namespace std;

char countSign(double number)
{
    unsigned char *byte = (unsigned char *)&number;
    int sign = byte[7] >> 7;
    if (sign == 1)
    {
        return '-';
    }
    return '+';
}

double countMantissa(double number)
{
    unsigned char *byte = (unsigned char *)&number;
    byte[7] = 0x3F;
    byte[6] = byte[6] | 0xF0;
    return number;
}

int countOrder(double number)
{
    unsigned char *byte = (unsigned char *)&number;
    int order = byte[7] & 0x7F;
    order = order << 4;
    order = order | ((byte[6] & 0xF0) >> 4);

    order = order - ((1 << 10) - 1);
    return order;
}

void printExponential(double number)
{
    cout << "Result: ";
    if (number == 0)
    {
        cout << 0;
        return;
    }

    cout.precision(20);
    cout << "Result: " << countSign(number) << countMantissa(number) << "*2^(" << countOrder(number) << ')';
}

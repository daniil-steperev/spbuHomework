#include <iostream>
#include <iomanip>
#include "convertToExponential.h"

using namespace std;

void countMantissa(unsigned char *bytes)
{
    unsigned char mantissaMask = 0b00001111; // because first 4 symbols for exponent
    double mantissa = 1 + (double)(bytes[6] & mantissaMask) / 16; // shift one for definiteness
    long long mantissaOrder = 16 * 256;

    for (int i = 5; i >= 0; i--)
    {
        mantissa += (double)bytes[i] / mantissaOrder;
        mantissaOrder *= 256;
    }
    cout << setprecision(20) << mantissa << "*2^";
}

void countSign(unsigned char *bytes)
{
    int sign = bytes[7] / 128;
    if (sign == 1)
    {
        cout << '-';
        return;
    }
    cout << '+';
}

void countOrder(unsigned char *bytes)
{
    int orderShift = 1023; // that's for double digits
    unsigned char orderMask = 0b01111111;
    unsigned char orderBeforeMantissa = 0b11110000;

    int order = (bytes[7] & orderMask) * 16 + (bytes[6] & orderBeforeMantissa) / 16 - orderShift;

    cout << "(" << order << ")";
}

void convertToExponential(double number)
{
    cout << "Result: ";
    if (number == 0.0)
    {
        cout << 0;
        return;
    }
    unsigned char *bytes = (unsigned char*)&number;
    countSign(bytes);
    countMantissa(bytes);
    countOrder(bytes);
}

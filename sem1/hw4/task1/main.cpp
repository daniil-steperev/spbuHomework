#include <iostream>
#include "cycleList.h"
#include "circleOfSikariya.h"

using namespace std;

int main()
{
    int numberOfPeople = 0;
    cout << "Enter the number of the people: ";
    cin >> numberOfPeople;
    int numberOfKilling = 0;
    cout << "\nEnter the number of killing: ";
    cin >> numberOfKilling;
    circleOfSikariya(numberOfPeople, numberOfKilling);
    return 0;
}

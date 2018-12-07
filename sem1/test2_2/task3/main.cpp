#include <iostream>

using namespace std;

const int maxName = 1000;

struct Man
{
    char *surname;
    int value;
};

void sortValues(int *values, int size)
{
    int key = 0;
    int i = 0;
    for (int j = 1; j < size; j++)
    {
        key = values[j];
        i = j - 1;
        while (i >= 0 && values[i] > key)
        {
            values[i + 1] = values[i];
            i = i - 1;
            values[i + 1] = key;
        }
    }
}

void printPerson(Man *people, int *values, int start, int size, int situation)
{
    for (int i = start; i < size; i++)
    {
        int j = 0;
        while (values[i] != people[j].value)
        {
            j++;
        }
        cout << people[j].surname;
        if (situation == 0)
        {
            cout << " should be killed!" << endl;
        }
        else if (situation == 1)
        {
            cout << "should be exiled" << endl;
        }
        else
        {
            cout << " should stay alive" << endl;
        }
    }
}

int main()
{
    int numberOfPeople = 0;
    cout << "Input number of people:";
    cin >> numberOfPeople;
    Man *people = new Man[numberOfPeople];
    int *values = new int[numberOfPeople] {0};
    for (int i = 0; i < numberOfPeople; i++)
    {
        char *surname = new char[maxName] {0};
        int value = 0;
        cin >> surname >> value;
        people[i] = Man{surname, value};
        values[i] = value;
    }

    sortValues(values, numberOfPeople);
    int numberOfKilled = 0;
    int numberOfExiled = 0;
    cout << "Enter number of killed and exiled: ";
    cin >> numberOfKilled >> numberOfExiled;

    printPerson(people, values, 0, numberOfKilled, 0); // "0" - should be killed

    printPerson(people, values, numberOfKilled, numberOfExiled + 1, 1); // "1" - should be exiled

    printPerson(people, values, numberOfKilled + numberOfExiled, numberOfPeople, 2); // "2" - should stay alive

    delete[] values;
    for (int i = 0; i < numberOfPeople; i++)
    {
        delete people[i].surname;
    }
    delete people;
}

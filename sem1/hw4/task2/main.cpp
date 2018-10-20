#include <iostream>
#include "list.h"
#include "base.h"
using namespace std;

void findUserName(List *list)
{
    cout << "Enter the phone: ";
    int phone = 0;
    cin >> phone;
    char *name = findNameByPhone(list, phone);
    if (name[0] == '\0')
    {
        cout << "Not found!" << endl;
    }
    else
    {
        cout << "Name: " << name << endl;
    }
}

void findUserPhone(List *list)
{
    cout << "Enter the name: ";
    char *name = new char[256];
    cin >> name;
    int phone = findPhoneByName(list, name);
    if (phone == -1)
    {
        cout << "Not found!" << endl;
    }
    else
    {
        cout << "Phone: " << phone << endl;
    }
}

void addNameAndPhone(List *list)
{
    cout << "Enter the name and the phone: ";
    char *name = new char [256];
    int phone = 0;
    cin >> name;
    cin >> phone;
    addToList(list, name, phone);
}

void hint()
{
    cout << "There are 5 functions: " << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add name and phone" << endl;
    cout << "2 - find phone by name" << endl;
    cout << "3 - find name by phone" << endl;
    cout << "4 - save to base" << endl;
    cout << "Enter the task: " << endl;
}

void menu(List *list)
{
    char request = '\0';
    hint();
    while (request != '0')
    {
        cin >> request;
        switch (request)
        {
            case '0':
                {
                     cout << "Thanks for using my program! Goodbye!" << endl;
                     break;
                }
            case '1':
                {
                    addNameAndPhone(list);
                    hint();
                    break;
                }
            case '2':
                {
                    findUserPhone(list);
                    hint();
                    break;
                }
            case '3':
                {
                    findUserName(list);
                    hint();
                    break;
                }
            case '4':
                {
                    saveBase(list);
                    hint();
                    break;
                }
        }
    }
}

int main()
{
    cout << "Good morning! Welcome to the Base 1 program!\n";
    List *list = createList();
    downloadBase(list);
    menu(list);
    deleteList(list);
    return 0;
}

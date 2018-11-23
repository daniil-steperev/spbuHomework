#include <iostream>

using namespace std;

int iterableFibbonachi(int number)
{
    int fibbonachi = 0;
    int fibFirst = 1;
    int fibSecond = 1;
    int counter = 2;
    while (counter < number)
    {
        fibbonachi = fibFirst + fibSecond;
        fibFirst = fibSecond;
        fibSecond = fibbonachi;
        counter += 1;
    }
    return fibbonachi;
}

int main()
{
    cout << "Enter the number of Fibbonachi: ";
    int number = 0;
    cin >> number;
    cout << endl;
    cout << "Fibbonachi number: ";
    cout << iterableFibbonachi(number);
}

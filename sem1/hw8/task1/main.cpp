#include <iostream>

using namespace std;

int findOriginal(int *studentWorks, int student)
{
    if (1 <= student  && student <= 3) // first three students
    {
        return student;
    }
    else if (studentWorks[student] == -1) // did not write a homework
    {
        return -1;
    }
    else
    {
        int variant = findOriginal(studentWorks, studentWorks[student]);
        studentWorks[student] = variant;
        return variant;
    }
}

void getPairs(int *studentWorks, int amountOfStudents)
{
    cout << "Enter pairs of students (without first three): " << endl;
    for (int i = 1; i <= amountOfStudents - 3; i++) // starting with 1 because we don't have student 0
    {
        int student = 0;
        cin >> student;
        int copiedWork = 0;
        cin >> copiedWork;
        studentWorks[student] = copiedWork;
    }
}

void printResult(int *studentWorks, int amountOfStudents)
{
    for (int i = 1; i <= amountOfStudents; i++)
    {
        int variant = findOriginal(studentWorks, i);
        if (variant == -1)
        {
            cout << "Student " << i << " must be deducted!" << endl; // did not write the homework
        }
        else
        {
            cout << "Student " << i << " had a " << variant << " variant!" << endl;
        }
    }
}

int main()
{
    int amountOfStudents = 0;
    cout << "Enter the amount of students: ";
    cin >> amountOfStudents;
    cout << endl;

    int *studentWorks = new int [amountOfStudents + 1] {0};
    for (int i = 1; i <= 3; i++) // first three students
    {
        studentWorks[i] = i;
    }
    getPairs(studentWorks, amountOfStudents);

    printResult(studentWorks, amountOfStudents);
    delete[] studentWorks;
    return 0;
}

#pragma once
#include <fstream>
using namespace std;
struct MyString
{
    char *content = nullptr;
    int size = 0;
};

MyString *createString();
MyString *createString(char *string);
void deleteString(MyString *string);

MyString *clone(MyString *string);
MyString *concatenate(MyString *stringFirst, MyString *stringSecond);

bool isEqual(MyString *stringFirst, MyString *strintSecond);
int countLength(MyString *string);
bool isEmpty(MyString *string);

MyString *subString(MyString *string, int index, int end);
char *returnChar(MyString *string);

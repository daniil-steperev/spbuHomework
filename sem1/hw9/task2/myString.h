#pragma once
#include <fstream>
using namespace std;
struct MyString
{
    char *content = nullptr;
    int size = 0;
};

MyString *createString();
MyString *createString(const char *string);
void deleteString(MyString *string);

MyString *clone(MyString *string);
MyString *concatenate(MyString *stringFirst, MyString *stringSecond);

bool isEqual(MyString *stringFirst, MyString *strintSecond);
int countLength(MyString *string);
bool isEmpty(MyString *string);

MyString *pickOutSubStr(MyString *string, const int index, const int length);
char *returnChar(MyString *string);
void writeString(ofstream &file, MyString *string);

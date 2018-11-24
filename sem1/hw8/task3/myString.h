#pragma once

struct MyString
{
    char *content = nullptr;
    int size = 0;
};

MyString *createString();
MyString *createString(const char *string);
void deleteString(MyString *string);

MyString *clone(MyString *string);
MyString *concatenate(MyString *firstString, MyString *secondString);

bool are_Equal(MyString *firstString, MyString *secondString);
int countLength(MyString *string);
bool is_Empty(MyString *string);

MyString *pickOutSubStr(MyString *string, const int index, const int length);
char *returnChar(MyString *string);

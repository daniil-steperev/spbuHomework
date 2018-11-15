#pragma once

struct MyString
{
    char *content = nullptr;
    int size = 0;
};

MyString *createString();
MyString *createString(const char *str);
void deleteString(MyString *str);

MyString *clone(MyString *str);
MyString *concatenate(MyString *str1, MyString *str2);

bool are_Equal(MyString *str1, MyString *str2);
int countLength(MyString *str);
bool is_Empty(MyString *str);

MyString *pickOutSubStr(MyString *str, const int index, const int length);
char *returnChar(MyString *str);

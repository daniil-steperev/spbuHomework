#include <stdio.h>
#include <string.h>

bool checkingForEmpty(char line[])
{
    int position = strlen(line) - 1;
    while ((position >= 0) && ((line[position] == ' ') || (line[position] == '\t') || (line[position] == '\n')))
    {
        position -= 1;
    }
    return position == -1;
}

int main()
{
    FILE *file = NULL;
    char name[] = "string.txt";
    file = fopen(name, "r");
    if (file != NULL)
    {
        char line[256] = {'\0'};
        int counter = 0;
        while (!feof(file))
        {
            fgets(line, 256, file);
            counter += checkingForEmpty(line);
        }
        printf("%d", counter);
    }
    else
    {
        printf("Can not open file :(");
    }
}

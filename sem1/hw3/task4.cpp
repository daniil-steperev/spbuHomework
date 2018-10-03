#include <stdio.h>

bool matchingCheck(int inputNumber, int *secretNumber)
{
    int listOfNum[4] = {0};
    for (int i = 0; i < 4; ++i)
    {
        listOfNum[i] = inputNumber % 10;
        inputNumber /= 10;
    }
    int cows = 0;
    int bulls = 0;
    for (int i = 0; i < 4; ++i)
    {
        if (listOfNum[i] == secretNumber[i])
            bulls += 1;
        for (int j = 0; j < 4; ++j)
            if ((listOfNum[i] == secretNumber[j]) && (i != j))
            cows += 1;
    }
    if (bulls != 4)
    {
        printf("%d COWS, %d BULLS", cows, bulls);
        return true;
    }
    return false;

}

void gameForTwoPlayers()
{
    printf("Enter the secret number: ");
    int secretNumber = 0;
    scanf("%d", &secretNumber);
    int listOfSecNum[4] = {0};
    for (int i = 0; i < 4; ++i)
    {
        listOfSecNum[i] = secretNumber % 10;
        secretNumber /= 10;
    }
    int inputNumber = 0;
    printf("Make a guess!: ");
    scanf("%d", &inputNumber);
    while (matchingCheck(inputNumber, listOfSecNum))
    {
        printf("\nMake a guess again!: ");
        scanf("%d", &inputNumber);
    }
    printf("YOU WIN!");
}

int main()
{
    gameForTwoPlayers();
    return 0;
}

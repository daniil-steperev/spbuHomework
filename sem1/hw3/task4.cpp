#include <stdio.h>
#include <stdlib.h>
#include <time.h>

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

void gameForTwoPlayers(int secretNumber = 0)
{
    if (secretNumber == 0)
    {
        printf("Enter the secret number: ");
        scanf("%d", &secretNumber);
    }
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

int checkingDigits(int secretNumber)
{
    int numbers[4] = {0};
    for (int i = 0; i < 4; ++i)
    {
        numbers[i] = secretNumber % 10;
        secretNumber /= 10;
    }
    for (int i = 0; i < 4; ++i)
    {
        int j = 0;
        while (j < i)
        {
            if (numbers[i] == numbers[j])
            {
                numbers[i] = (numbers[j] + 1) % 10;
                j = 0;
            }
            j += 1;
        }
    }
    for (int i = 0; i < 4; ++i)
    {
        secretNumber *= 10;
        secretNumber += numbers[i];
    }
    return secretNumber;
}

void gameForOnePlayer()
{
    srand(time(0));
    int secretNumber = 1234 + rand() % (10000 - 1234 + 1);
    secretNumber = checkingDigits(secretNumber);
    gameForTwoPlayers(secretNumber);
}

int main()
{
    printf("Do you want to play with computer? (Yes/no) ");
    char answer[3] = {0};
    scanf("%s", answer);
    if (answer[0] == 'Y' || answer[0] == 'y')
        gameForOnePlayer();
    else
        gameForTwoPlayers();
    return 0;
}

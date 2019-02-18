#include <iostream>
#include "graph.h"
#include "aStar.h"

using namespace std;

int main()
{
    ifstream file("input.txt");

    if (!file.is_open())
    {
        cout << "File cannot be opened!";
        return 0;
    }
    int startX = 0;
    int startY = 0;
    file >> startX >> startY;

    int finishX = 0;
    int finishY = 0;
    file >> finishX >> finishY;

    Graph *map = createGraph(file);

    algorithmAStar(map->matrix, map->size, startX, startY, finishX, finishY);

    deleteGraph(map);
    file.close();
    return 0;
}

#pragma once
#include <fstream>
#include "list.h"

void mainEncoding(ifstream &file, ofstream &fileOutput);
void readCharactersCodes(char *buff, List *characters);
void translateMessage(ofstream &file, List *characters, char *message);

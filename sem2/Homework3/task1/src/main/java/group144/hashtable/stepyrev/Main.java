package group144.hashtable.stepyrev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws WrongInputException, IOException {
        System.out.println("Welcome to the HashTable program!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashTable hashTable = new HashTable();
        String task = "";

        while (!task.equals("exit")) {
            printMenu();
            task = reader.readLine();

            switch (task) {
                case "exit": {
                    System.out.println("Thanks for using my program!");
                    break;
                }
                case "add": {
                    String element = "";
                    System.out.println("Enter an element to add: ");
                    element = reader.readLine();

                    hashTable.add(element);
                    break;
                }
                case "remove": {
                    String element = "";
                    System.out.println("Enter an element to remove: ");
                    element = reader.readLine();
                    try {
                        hashTable.remove(element);
                    }
                    catch (ElementAbsenseException e) {
                        System.out.println("Element is not in the HashTable!");
                    }
                    finally {
                        break;
                    }
                }
                case "find": {
                    String element = "";
                    System.out.println("Enter an element to find: ");
                    element = reader.readLine();
                    if (hashTable.find(element)) {
                        System.out.println("Element " + element + " is in HashTable!");
                    }
                    else {
                        System.out.println("Element " + element + " is not in HashTable!");
                    }
                    break;
                }
                case "gs": {
                    System.out.println("HashTable statistic: " + hashTable.getStatistics());
                    break;
                }
                case "fff": {
                    System.out.println("Enter a file name: ");
                    String fileName = reader.readLine();
                    hashTable.getFromFile(fileName);
                    break;
                }
                case "chf": {
                    System.out.println("There are two HashFunctions: polynomial and summary.");
                    System.out.println("If you want to use polynomial HashFunction enter 0");
                    System.out.println("If you want to use summary HashFunction enter 1");
                    HashFunction newHashFunction = null;

                    int typeOfHashFunction = Integer.parseInt(reader.readLine());
                    if (typeOfHashFunction == 0) {
                        int power = 0;
                        int mod = 0;

                        System.out.println("Enter the power of HashFunction: ");
                        power = Integer.parseInt(reader.readLine());

                        System.out.println("Enter the mod of HashFucntion: ");
                        mod = Integer.parseInt(reader.readLine());

                        try {
                            newHashFunction = new PolynomialHashFunction(power, mod);
                        }
                        catch (WrongInputException e) {
                            System.out.println("Inputed data is not correct! Please, check that power and mod >1.");
                            break;
                        }

                    }
                    else if (typeOfHashFunction == 1) {
                        int newMod = 0;
                        System.out.print("Enter the mod of SummaryHashFunction (>1): ");
                        newMod = Integer.parseInt(reader.readLine());

                        try {
                            newHashFunction = new SummaryHashFunction(newMod);
                        }
                        catch (WrongInputException e) {
                            System.out.println("Inputed data is not correct! Please, check that power and mod >1.");
                            break;
                        }
                    }
                    else {
                        System.out.println("Entered task is not correct! Please, make sure that you are entering correct data.");
                        break;
                    }

                    hashTable.changeHashFunction(newHashFunction);
                }

            }
        }

        reader.close();
    }

    public static void printMenu() {
        System.out.println("");
        System.out.println("Type a command to perform with HashTable: ");
        System.out.println("exit - stops the program");
        System.out.println("add - adds new value to the HashTable");
        System.out.println("remove - removes from the HashTable");
        System.out.println("find - finds element in the HashTable");
        System.out.println("gs - gets a HashTable's statistic");
        System.out.println("fff - fills the HashTable with data from the file");
        System.out.println("chf - chooses HashFunction from the proposed choice");
        System.out.print("Enter a task: ");
    }
}

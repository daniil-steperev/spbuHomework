package group144.hashtable.stepyrev;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    private static HashTable hashTable;

    @Test
    void findWithPositiveAnswer() throws WrongInputException {
        HashTable hashTable = new HashTable();
        hashTable.add("abc");

        assertEquals(true, hashTable.find("abc"));
    }

    @Test
    void findWithNegativeAnswer() throws WrongInputException {
        HashTable hashTable = new HashTable();

        assertEquals(false, hashTable.find("abc"));
    }

    @Test
    void removeMissingElement() throws WrongInputException {
        HashTable hashTable = new HashTable();

        assertThrows(ElementAbsenseException.class, () -> hashTable.remove("abc"));
    }

    @Test
    void getFromFileTest() throws WrongInputException {
        HashTable hashTable = new HashTable();
        String fileName = "testFile.txt";
        hashTable.getFromFile(fileName);
        String rightStatistics = "The amount of elements: 8; load factor: 0.03125; number of conflicts: 0; max length of conflict row: 0; empty rows: 248.";

        assertEquals(rightStatistics, hashTable.getStatistics());
    }
}
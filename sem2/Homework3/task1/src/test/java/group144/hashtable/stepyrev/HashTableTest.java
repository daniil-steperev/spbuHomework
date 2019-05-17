package group144.hashtable.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
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
        Statistics correctStatistics = new Statistics(0.03125, 0, 0, 248, 8);

        assertTrue(correctStatistics.equals(hashTable.getStatistics()));
    }
}
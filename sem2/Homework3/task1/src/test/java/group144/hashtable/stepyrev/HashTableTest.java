package group144.hashtable.stepyrev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
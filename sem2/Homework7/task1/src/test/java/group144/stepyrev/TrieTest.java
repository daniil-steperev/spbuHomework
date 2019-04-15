package group144.stepyrev;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    public void addSomeElementsTest() {
        Trie trie = new Trie();

        assertTrue(trie.add("errors"));
        assertTrue(trie.add("badCode"));
        assertFalse(trie.add("errors"));
        assertFalse(trie.add("badCode"));
        assertEquals(2, trie.size());
    }

    @Test
    public void removeSomeElementsTest() {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("bcd");
        assertTrue(trie.remove("abc"));
        assertTrue(trie.remove("bcd"));
        assertEquals(0, trie.size());
    }

    @Test
    public void containsTest() {
        Trie trie = new Trie();
        trie.add("errors");
        trie.add("badCode");
        assertTrue(trie.contains("errors"));
        assertTrue(trie.contains("badCode"));
        assertFalse(trie.contains("goodCode"));
        assertFalse(trie.contains("rationality"));
    }

    @Test
    public void howManyStartWithPrefixTest() {
        Trie trie = new Trie();
        trie.add("mistakes");
        trie.add("errors");
        trie.add("motherFather");
        trie.add("motorcycle");

        assertEquals(3, trie.howManyStartWithPrefix("m"));
        assertEquals(2, trie.howManyStartWithPrefix("mo"));
        assertEquals(0, trie.howManyStartWithPrefix("goodCode"));
    }

    @Test
    public void serializationTest() throws IOException, ClassNotFoundException {
        Trie trie = new Trie();
        trie.add("i");
        trie.add("love");
        trie.add("java");

        String fileName = "test.txt";
        trie.serialize(new FileOutputStream(new File(fileName)));

        FileInputStream inputStream = new FileInputStream(new File(fileName));
        trie.deserialize(inputStream);

        assertEquals(3, trie.size());
        assertTrue(trie.contains("i"));
        assertTrue(trie.contains("love"));
        assertTrue(trie.contains("java"));

        inputStream.close();
    }
}
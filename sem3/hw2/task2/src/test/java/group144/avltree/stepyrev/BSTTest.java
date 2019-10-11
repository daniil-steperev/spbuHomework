package group144.avltree.stepyrev;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private final int TEST_NUMBER = 1000;

    @Test
    public void simpleAddTest() {
        BST<Integer> tree = new BST<>();

        tree.add(1);
        assertEquals( 1, tree.getSize());

        tree.add(2);
        assertEquals( 2, tree.getSize());

        tree.add(2);
        assertEquals( 2, tree.getSize());
    }

    @Test
    public void hugeAddTest() {
        BST<Integer> tree = new BST<>();

        for (int i = 0; i < TEST_NUMBER; i++) {
            tree.add(i);
            assertEquals( i + 1, tree.getSize());
        }
    }

    @Test
    public void simpleRemoveTest() {
        BST<Integer> tree = new BST<>();

        tree.add(1);
        assertEquals( 1, tree.getSize());

        tree.add(2);
        assertEquals( 2, tree.getSize());

        tree.add(3);
        assertEquals(3, tree.getSize());

        tree.remove(1);
        assertEquals(2, tree.getSize());

        tree.remove(2);
        assertEquals(1, tree.getSize());

        tree.remove(2);
        assertEquals(1, tree.getSize());
    }

    @Test
    public void containsTest() {
        BST<Integer> tree = new BST<>();

        tree.add(1);
        tree.add(5);
        tree.add(10);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(10));

        assertFalse(tree.contains(2));
        assertFalse(tree.contains(7));
        assertFalse(tree.contains(20));
    }

    @Test
    public void tosStringTest() {
        BST<Integer> tree = new BST<>();

        for (int i = 0; i < 5; i++) {
            tree.add(i);
        }


        assertEquals("0  1  2  3  4  ", tree.toString());
    }

    @Test
    public void simpleIteratorTest() {
        BST<Integer> tree = new BST<>();

        for (int i = 0; i < 5; i++) {
            tree.add(i);
        }

        Iterator<Integer> iterator = tree.iterator();

        assertTrue(iterator.hasNext());

        assertEquals(Integer.valueOf(0), iterator.next());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void noSuchElementTest() {
        BST<Integer> tree = new BST<>();
        Iterator<Integer> iterator = tree.iterator();

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void iteratorRemoveTest() {
        BST<Integer> tree = new BST<>();

        for (int i = 0; i < 10; i++) {
            tree.add(i);
        }

        Iterator<Integer> iterator = tree.iterator();

        assertTrue(tree.contains(0));
        assertEquals(Integer.valueOf(0), iterator.next());
        iterator.remove();
        assertFalse(tree.contains(0));

        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());

        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(tree.contains(3));
        iterator.remove();
        assertFalse(tree.contains(3));
    }


    /**
     *          5
     *         /\
     *        3  6
     *       /\  \
     *      2 4   8
     */
    @Test
    public void forEachIteratorTest() {
        BST<Integer> tree = new BST<>();

        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(6);
        tree.add(2);
        tree.add(8);

        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 8);
        int curIndex = 0;
        for (Integer number : tree) {
            assertEquals(numbers.get(curIndex), number);
            curIndex++;
        }
    }

    @Test
    public void twoIteratorsTest() {
        BST<Integer> tree = new BST<>();

        tree.add(3);
        tree.add(2);
        tree.add(5);

        Iterator<Integer> firstIterator = tree.iterator();
        Iterator<Integer> secondIterator = tree.iterator();

        firstIterator.next();
        assertEquals(Integer.valueOf(3), firstIterator.next());
        firstIterator.remove();

        secondIterator.next();
        assertFalse(secondIterator.next().equals(3));
    }
}
package group144.avltree.stepyrev;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    @Test
    public void makeRightRotate() {
        AVLTree<Integer> tree = new AVLTree<>();
        String balancedTree = "(4 (3 null null) (5 null null))";
        tree.add(5);
        tree.add(4);
        tree.add(3);

        assertEquals(balancedTree, tree.toString());
    }

    @Test
    public void makeLeftRotate() {
        AVLTree<Integer> tree = new AVLTree<>();
        String balancedTree = "(2 (1 null null) (3 null null))";
        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(balancedTree, tree.toString());
    }

    @Test
    public void addSimilarElements() {
        AVLTree<Integer> tree = new AVLTree<>();
        String normalTree = "(1 null null)";
        tree.add(1);
        assertFalse(tree.add(1));
        assertFalse(tree.add(1));

        assertEquals(normalTree, tree.toString());
    }

    @Test
    public void addFirstNineElements() {
        AVLTree<Integer> tree = new AVLTree<>();
        String balancedTree = "(4 (2 (1 null null) (3 null null)) (6 (5 null null) (8 (7 null null) (9 null null))))";
        for (int i = 1; i <= 9; i++) {
            tree.add(i);
        }

        assertEquals(balancedTree, tree.toString());
    }

    @Test
    public void makeBigRotate() {
        AVLTree<Integer> tree = new AVLTree<>();
        String balancedTree = "(15 (10 (5 null null) null) (30 (20 null null) (40 null null)))";
        tree.add(30);
        tree.add(15);
        tree.add(40);
        tree.add(10);
        tree.add(20);
        tree.add(5);

        assertEquals(balancedTree, tree.toString());
    }

    @Test
    public void removeRoot() {
        AVLTree<Integer> tree = new AVLTree<>();
        String normalTree = "(6 null null)";
        tree.add(5);
        tree.add(6);
        tree.remove(5);

        assertEquals(normalTree, tree.toString());
    }

    @Test
    public void removeAloneElement() {
        AVLTree<Integer> tree = new AVLTree<>();
        String normalTree = "(2 null (3 null null))";
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.remove(1);

        assertEquals(normalTree, tree.toString());
    }

    @Test
    public void removeRightest() {
        AVLTree<Integer> tree = new AVLTree<>();
        String normalTree = "(5 (4 (3 null null) null) (7 (6 null null) (9 null (10 null null))))";
        tree.add(5);
        tree.add(4);
        tree.add(8);
        tree.add(3);
        tree.add(7);
        tree.add(9);
        tree.add(6);
        tree.add(10);
        tree.remove(8);

        assertEquals(normalTree, tree.toString());
    }

    @Test
    public void sizeTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        int normalSize = 4;
        tree.add(4);
        tree.add(2);
        tree.add(11);
        tree.add(411);
        tree.add(1112);
        tree.remove(4);

        assertEquals(normalSize, tree.size());
    }

    @Test
    public void containsTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(21);
        tree.add(21313);

        assertTrue(tree.contains(21));
    }

    @Test
    public void iteratorTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(4);
        tree.add(2);
        tree.add(5);
        Iterator<Integer> treeIterator = tree.iterator();
        assertTrue(treeIterator.hasNext());
        assertEquals(Integer.valueOf(2), treeIterator.next());
        assertTrue(treeIterator.hasNext());
        assertEquals(Integer.valueOf(4), treeIterator.next());
        assertTrue(treeIterator.hasNext());
        assertEquals(Integer.valueOf(5), treeIterator.next());
        assertFalse(treeIterator.hasNext());
    }

    @Test
    public void iteratorEmptyTreeTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        Iterator<Integer> treeIterator = tree.iterator();
        assertFalse(treeIterator.hasNext());

        for (int i = 0; i < 10; i++) {
            assertEquals(null, treeIterator.next());
        }
    }

    @Test
    public void toArrayTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        Integer[] normalArray = new Integer[] {1, 2, 3};
        tree.add(2);
        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertArrayEquals(normalArray, tree.toArray());
    }

    @Test
    public void containingAllTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        assertTrue(tree.containsAll(collection));
    }

    @Test
    public void removeAllTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        collection.add(1);
        collection.add(2);

        assertTrue(tree.removeAll(collection));
    }

    @Test
    public void retainAll() {
        AVLTree<Integer> tree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        collection.add(1);
        collection.add(2);

        assertTrue(tree.retainAll(collection));
    }

    @Test
    public void addString() {
        AVLTree<String> tree = new AVLTree<>();
        String normalTree = "(ABC (AAA null null) (BCD null null))";
        tree.add("ABC");
        tree.add("BCD");
        tree.add("AAA");

        assertEquals(normalTree, tree.toString());
    }

    @Test
    public void removeString() {
        AVLTree<String> tree = new AVLTree<>();
        String normalTree = "(AAA null (BCD null null))";
        tree.add("ABC");
        tree.add("BCD");
        tree.add("AAA");
        tree.remove("ABC");

        assertEquals(normalTree, tree.toString());
    }
}
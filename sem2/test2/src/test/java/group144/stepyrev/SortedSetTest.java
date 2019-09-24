package group144.stepyrev;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SortedSetTest {
    @Test
    public void addTest() {
        SortedSet<String> sortedSet = new SortedSet<>();

        LinkedList<String> firstList = new LinkedList<>();
        Collections.addAll(firstList, "a", "b", "abc");

        LinkedList<String> secondList = new LinkedList<>();
        Collections.addAll(secondList, "dad", "asda");

        LinkedList<String> thirdList = new LinkedList<>();
        Collections.addAll(thirdList, "daasdad", "dasdca", "aasd", "sadad");

        assertTrue(sortedSet.add(firstList));
        assertTrue(sortedSet.add(secondList));
        assertTrue(sortedSet.add(thirdList));

        assertEquals(3, sortedSet.getSize());
    }

    @Test
    public void getElementsTest() {
        SortedSet<String> sortedSet = new SortedSet<>();

        LinkedList<String> firstList = new LinkedList<>();
        Collections.addAll(firstList, "a", "b");

        LinkedList<String> secondList = new LinkedList<>();
        Collections.addAll(secondList, "d", "e", "abc");


        assertTrue(sortedSet.add(firstList));
        assertTrue(sortedSet.add(secondList));

        String elements = "a b d e abc ";
        assertEquals(elements, sortedSet.getElements());
    }

}
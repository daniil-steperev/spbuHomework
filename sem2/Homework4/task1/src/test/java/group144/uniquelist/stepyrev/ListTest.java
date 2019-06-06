package group144.uniquelist.stepyrev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    @Test
    void addFirstToEmptyList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(5);
        String printedList = "5";

        assertEquals(printedList, list.print());
    }

    @Test
    void addFirstToList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        StringBuilder printedList = new StringBuilder();
        printedList.append("1 2 3");

        assertEquals(printedList.toString(), list.print());
    }

    @Test
    void addLastToEmptyList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addLast(5);
        String printedList = "5";

        assertEquals(printedList, list.print());
    }

    @Test
    void addLastToList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        String printedList = "1 2 3";

        assertEquals(printedList, list.print());
    }

    @Test
    void addToEmptyList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.add(5, 0);
        String printedList = "5";

        assertEquals(printedList, list.print());
    }

    @Test
    void addToListWithNegativeindex() {
        MyList<Integer> list = new MyList<>();
        assertThrows(WrongIndexException.class, () -> list.add(4, -4));
    }

    @Test
    void addToList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.add(3, 0);
        list.add(1, 0);
        list.add(2, 1);
        String printedList = "1 2 3";

        assertEquals(printedList, list.print());
    }

    @Test
    void removeFirstFromEmptyList() {
        MyList<Integer> list = new MyList<>();

        assertThrows(EmptyListException.class, () -> list.removeFirst());
    }

    @Test
    void removeFirstFromList() throws AlreadyAddedElementException, EmptyListException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(2);
        list.addFirst(1);

        assertEquals(Integer.valueOf(1), list.removeFirst());
    }

    @Test
    void removeLastFromEmptyList() {
        MyList<Integer> list = new MyList<>();

        assertThrows(EmptyListException.class, () -> list.removeLast());
    }

    @Test
    void removeLastFromList() throws AlreadyAddedElementException, EmptyListException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(3);
        list.addFirst(2);

        assertEquals(Integer.valueOf(3), list.removeLast());
    }

    @Test
    void removeFromEmptyListByValue() {
        MyList<Integer> list = new MyList<>();

        assertThrows(EmptyListException.class, () -> list.remove(Integer.valueOf(4)));
    }

    @Test
    void removeFromEmptyListByIndex() {
        MyList<Integer> list = new MyList<>();

        assertThrows(EmptyListException.class, () -> list.remove(4));
    }

    @Test
    void removeFromListByValue() throws AlreadyAddedElementException, ElementNotFoundException, EmptyListException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        String printedList = "1 3";

        list.removeByValue(Integer.valueOf(2));
        assertEquals(printedList, list.print());
    }

    @Test
    void removeFromListByIndex() throws AlreadyAddedElementException, EmptyListException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        String printedList = "1 3";

        list.remove(1);
        assertEquals(printedList, list.print());
    }

    @Test
    void isContainInEmptyList() {
        MyList<Integer> list = new MyList<>();

        assertEquals(false, list.contains(Integer.valueOf(2)));
    }

    @Test
    void isContainInList() throws AlreadyAddedElementException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(2);

        assertEquals(true, list.contains(Integer.valueOf(2)));
    }

    @Test
    void getIndexOfElementInEmptyList() {
        MyList<Integer> list = new MyList<>();

        assertThrows(EmptyListException.class, () -> list.getIndexOf(Integer.valueOf(2)));
    }

    @Test
    void getIndexOfElementInList() throws AlreadyAddedElementException, ElementNotFoundException, EmptyListException, WrongIndexException {
        MyList<Integer> list = new MyList<>();
        list.addFirst(2);

        assertEquals(0, list.getIndexOf(Integer.valueOf(2)));
    }
}
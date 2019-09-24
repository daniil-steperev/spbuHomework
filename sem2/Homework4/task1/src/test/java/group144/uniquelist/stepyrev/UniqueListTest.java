package group144.uniquelist.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {
    @Test
    void addFirstSimilarOne() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);

        assertThrows(AlreadyAddedElementException.class, () -> list.addFirst(4));
    }

    @Test
    void addFirst() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);
        String printedList = "1 4";

        list.addFirst(1);
        assertEquals(printedList, list.print());
    }

    @Test
    void addLastSimilarOne() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);
        list.addFirst(10);
        list.addFirst(1);

        assertThrows(AlreadyAddedElementException.class, () -> list.addLast(4));
    }

    @Test
    void addLast() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);
        list.addFirst(10);
        list.addFirst(1);
        String printedList = "1 10 4 2";

        list.addLast(2);
        assertEquals(printedList, list.print());
    }

    @Test
    void addSimilarOne() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);
        list.addFirst(10);
        list.addFirst(1);

        assertThrows(AlreadyAddedElementException.class, () -> list.add(1, 0));
    }

    @Test
    void add() throws AlreadyAddedElementException, WrongIndexException {
        UniqueList<Integer> list = new UniqueList<>();
        list.addFirst(4);
        list.addFirst(10);
        list.addFirst(1);
        String printedList = "1 10 2 4";

        list.add(2, 2);
        assertEquals(printedList, list.print());
    }
}
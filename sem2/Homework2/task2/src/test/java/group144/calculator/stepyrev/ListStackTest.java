package group144.calculator.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {
    private static ListStack<Integer> stack = new ListStack<Integer>();

    @Test
    void pushOneElement() throws EmptyStackException {
        stack.push(2);
        assertEquals(Integer.valueOf(2), stack.pop());
    }

    @Test
    void pushSeveralElements() throws EmptyStackException {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            array[i] = i;
        }

        for (int i = 9; i >= 0; i--) {
            assertEquals(Integer.valueOf(array[i]), stack.pop());
        }
    }

    @Test
    void popOneElement() throws EmptyStackException {
        stack.push(2);
        assertEquals(Integer.valueOf(2), stack.pop());
    }

    @Test
    void popFromEmptyStack() throws EmptyStackException {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }
}
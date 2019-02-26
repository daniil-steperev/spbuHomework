package group144.calculator.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    private static ArrayStack<Integer> stack = new ArrayStack<Integer>();

    @Test
    void pushOneElement() {
        stack.push(2);
        assertEquals(2, stack.pop());
    }

    @Test
    void pushSeveralElements() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            array[i] = i;
        }

        for (int i = 9; i >= 0; i--) {
            assertEquals(array[i], stack.pop());
        }
    }

    @Test
    void popOneElement() {
        stack.push(2);
        assertEquals(2, stack.pop());
    }

    @Test
    void popFromEmptyStack() {
        assertEquals(null, stack.pop());
    }
}
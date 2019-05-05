package group144.queue.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    @Test
    void dequeueEmptyQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        assertThrows(EmptyQueueException.class, () -> queue.dequeue());
    }

    @Test
    void dequeueMethodTest() throws EmptyQueueException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(1, 10);
        queue.enqueue(2, 12);
        queue.enqueue(3, 2);
        queue.enqueue(4, 1);
        queue.enqueue(5, 5);

        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(Integer.valueOf(4), queue.dequeue());
    }

    @Test
    void getLengthTest() throws EmptyQueueException {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.enqueue("abc", 10);
        queue.enqueue("def", 110);
        queue.enqueue("gkl", 5);
        queue.enqueue("mno", 3);
        queue.dequeue();
        queue.dequeue();

        assertEquals(2, queue.getLength());
    }

    @Test
    void enqueueElementWithSimilarPriorities() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(1, 2);
        queue.enqueue(2, 2);
        queue.enqueue(3, 2);
        queue.enqueue(4, 2);
        String queueElements = "1 2 3 4";

        assertEquals(queueElements, queue.toString());
    }

    @Test
    void enqueueElementsWithDifferentPriorities() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(5, 2);
        queue.enqueue(6, 3);
        queue.enqueue(7, 10);
        queue.enqueue(8, 5);
        String queueElements = "7 8 6 5";

        assertEquals(queueElements, queue.toString());
    }
}
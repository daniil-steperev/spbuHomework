package group144.queue.stepyrev;

/** An exception that should be thrown when method dequeue is raised for empty queue. */
public class EmptyQueueException extends Exception {
    /** A constructor of the EmptyQueueException. */
    public EmptyQueueException() {
        super();
    }

    /** A constructor of the EmptyQueueException. */
    public EmptyQueueException(String msg) {
        super(msg);
    }
}

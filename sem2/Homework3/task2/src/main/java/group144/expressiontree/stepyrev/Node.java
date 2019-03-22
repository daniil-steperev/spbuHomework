package group144.expressiontree.stepyrev;

/** An abstract class that represents one tree element that includes operation/operand and two descendants */
abstract class Node {
    /** A method that calculates value of the Node */
    abstract int calculate() throws WrongInputException;

    /** A method that prints the Node */
    abstract void print();
}

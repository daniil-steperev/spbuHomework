package group144.expressiontree.stepyrev;

/** An interface that represents one tree element that includes operation/operand and two descendants */
interface Node {
    /** A method that calculates value of the Node */
    int calculate() throws WrongInputException;

    /** A method that prints the Node */
    void print();
}

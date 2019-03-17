package group144.expressiontree.stepyrev;

/** A class that represents an operant from the expression tree */
public class OperandNode extends Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    /** A constructor of Operand Node */
    public OperandNode(int value) {
        this.value = value;
    }

    /** {@inheritDoc}*/
    @Override
    public int calculate() {
        return value;
    }

    /** {@inheritDoc}*/
    @Override
    public void print() {
        System.out.print(value);
    }
}

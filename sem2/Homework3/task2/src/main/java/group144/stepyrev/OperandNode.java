package group144.stepyrev;

/** A class that represents an operand from the expression tree */
public class OperandNode implements Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    /** A constructor of Operand Node */
    public OperandNode(int value) {
        this.value = value;
    }

    /**
     * A construcot of Operand Node
     * @param value - a value of node
     * @param leftChild - a left child
     * @param rightChild - a right child
     */
    public OperandNode(int value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
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

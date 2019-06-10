package group144.stepyrev;

/** A class that represents an operand from the expression tree */
public class OperandNode implements Node {
    private int value;

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

package group144.stepyrev;

/** A class that represents an operator node from the expression tree */
public class OperatorNode implements Node {
    private int operation;
    private Node leftChild;
    private Node rightChild;

    /** A constructor of Operator Node */
    public OperatorNode(int operation) {
        this.operation = operation;
    }

    /**
     * A method that sets the left child of the current Operator Node
     * @param node means Node that should be set as child
     */
    public void setLeftChild(Node node) { leftChild = node; }

    /**
     * A method that sets the right child of the current Operator Node
     * @param node means Node that should be set as child
     */
    public void setRightChild(Node node) {
        rightChild = node;
    }

    /** {@inheritDoc}*/
    @Override
    public int calculate() {
        if (operation == '+') {
            return leftChild.calculate() + rightChild.calculate();
        }
        else if (operation == '-') {
            return leftChild.calculate() - rightChild.calculate();
        }
        else if (operation == '*') {
            return leftChild.calculate() * rightChild.calculate();
        }
        else if (operation == '/') {
            return leftChild.calculate() / rightChild.calculate();
        }

        return 0;
    }

    /** {@inheritDoc}*/
    @Override
    public void print() {
        System.out.print("(");
        leftChild.print();
        System.out.print(" " + operation + " ");
        rightChild.print();
        System.out.print(")");
    }
}

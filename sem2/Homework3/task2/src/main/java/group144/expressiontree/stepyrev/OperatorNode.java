package group144.expressiontree.stepyrev;

/** A class that represents an operator node from the expression tree */
public class OperatorNode extends Node {
    private char operation;
    private Node leftChild;
    private Node rightChild;

    /** A constructor of Operator Node */
    public OperatorNode(char operation) {
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
    public int calculate() throws WrongInputException {
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

        throw new WrongInputException();
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

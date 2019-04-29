package group144.expressiontree.stepyrev;

/** A class that represents the expression tree structure */
public class ExpressionTree {
    private Node head;

    /**
     * A method that builds the expression tree
     * @param expression means inputted expression based on which should be expression tree
     * @throws WrongInputException an expression that should be raised when user entered incorrect expression
     */
    public ExpressionTree(String expression) throws WrongInputException {
        head = (Node) scanTree(expression, 0).object;

        if (head instanceof OperandNode) {
            throw new WrongInputException();
        }
    }

    /**
     * A method that calculates result of expression tree
     * @return a result that was calculated
     * @throws WrongInputException an expression that should be raised when user entered incorrect expression
     */
    public int calculate() throws WrongInputException {
        int result = head.calculate();

        return result;
    }

    /** A method that prints the expression tree */
    public void print() {
        head.print();
    }

    private Pair scanTree(String expression, int index) throws WrongInputException {
        char symbol = expression.charAt(index);
        index++;
        Node newNode = null; // newNode would be initialized later

        if (symbol == '(') {
            symbol = expression.charAt(index); // get operation
            index++; // get space
            index++; // move to next symbol

            if (!isOperation(symbol)) {
                throw new WrongInputException();
            }

            newNode = new OperatorNode(symbol);
            Pair<Node> scannedElements = scanTree(expression, index);
            ((OperatorNode) newNode).setLeftChild(scannedElements.object);
            index = scannedElements.index;

            index++; // get space

            scannedElements = scanTree(expression, index);
            ((OperatorNode) newNode).setRightChild(scannedElements.object);
            index = scannedElements.index;

            index++; // move to next symbol
        } else {
            Pair<Integer> pairOfInts = countNumber(expression, index, symbol);
            index = pairOfInts.index;
            Integer number = (pairOfInts.object);

            newNode = new OperandNode(number.intValue());
        }

        return new Pair(newNode, index);
    }

    private Pair countNumber(String expression, int index, char symbol) throws WrongInputException {
        int number = makeInt(symbol);
        while (isNumber(expression.charAt(index))) {
            symbol = expression.charAt(index);
            index++; // move to the next element
            number = number * 10 + makeInt(symbol);
        }

        return new Pair(Integer.valueOf(number), index);
    }

    private boolean isOperation(char symbol) {
        return (symbol == '+') || (symbol == '-') ||
                (symbol == '/') || (symbol == '*');
    }

    private boolean isNumber(char symbol) {
        return symbol <= '9' && symbol >= '0';
    }

    private int makeInt(char symbol) {
        return symbol - '0';
    }

    private class Pair<T> {
        private T object;
        private int index;

        private Pair(T object, int index) {
            this.object = object;
            this.index = index;
        }
    }
}

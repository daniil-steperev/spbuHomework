package group144.expressiontree.stepyrev;

/** A class that represents the expression tree structure */
public class ExpressionTree {
    private Node head;

    /**
     * A method that builds the expression tree
     * @param expression means inputted expression based on which should be expression tree
     * @param index means index of current symbol
     * @throws WrongInputException an expression that should be raised when user entered incorrect expression
     */
    public ExpressionTree(String expression, int index) throws WrongInputException {
        head = (Node) scanTree(expression, index).object;

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
            Pair scanedElements = scanTree(expression, index);
            ((OperatorNode) newNode).setLeftChild((Node) scanedElements.object);
            index = scanedElements.index;

            index++; // get space

            scanedElements = scanTree(expression, index);
            ((OperatorNode) newNode).setRightChild((Node) scanedElements.object);
            index = scanedElements.index;

            index++; // move to next symbol
        } else {
            Pair pairOfInts = countNumber(expression, index, symbol);
            index = pairOfInts.index;
            Integer number = (Integer) pairOfInts.object;

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

    private class Pair {
        private Object object;
        private int index;

        private Pair(Object object, int index) {
            this.object = object;
            this.index = index;
        }
    }
}

package group144.stepyrev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A class that represents the expression tree structure */
public class ExpressionTree {
    private Node head = null;

    /**
     * A method that gets a tree from the file.
     * @param fileName - a file name from which a tree should be got
     * @throws IOException if reader cannot read from file
     */
    public void getTree(String fileName) throws IOException {
        try {
            FileReader reader = new FileReader(fileName);
            reader.read(); // get open bracket
            head = buildTree(reader);

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be read!");
        }
    }

    private Node buildTree(FileReader reader) throws IOException {
        int element = reader.read(); // get operation
        OperatorNode operation = new OperatorNode(element);

        reader.read(); // get space
        element = reader.read();
        if (element == '(') {
            operation.setLeftChild(buildTree(reader));
        } else {
            int number = countNumber(reader, element);
            operation.setLeftChild(new OperandNode(number));
        }

        element = reader.read(); // get next element
        if (element == '(') {
            operation.setRightChild(buildTree(reader));
        } else {
            int number = countNumber(reader, element);
            operation.setRightChild(new OperandNode(number));
        }

        reader.read(); // get space
        return operation;
    }

    /**
     * A method that calculates result of expression tree
     * @return a result that was calculated
     */
    public int calculate() {
        int result = head.calculate();

        return result;
    }

    /** A method that prints the expression tree */
    public void print() {
        head.print();
    }


    private int countNumber(FileReader reader, int element) throws IOException {
        int number = 0;
        while (!isEndOfNumber(element)) {
            number = number * 10 + makeInt(element);
            element = reader.read();
        }

        return number;
    }

    private boolean isEndOfNumber(int element) {
        return element == ' ' || element == ')';
    }

    private int makeInt(int symbol) {
        return symbol - '0';
    }
}

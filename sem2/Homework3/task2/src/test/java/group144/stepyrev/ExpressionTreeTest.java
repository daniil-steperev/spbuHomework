package group144.stepyrev;

import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    private ExpressionTree tree;
    private String fileName = "";

    @Test
    void calculateSimpleExpression() throws IOException {
        fileName = "src/test/resources/simpleExpression.txt";
        tree = new ExpressionTree(fileName);

        assertEquals(2, tree.calculate());
    }

    @Test
    void calculateExpressionFromTask() throws IOException {
        fileName = "src/test/resources/expressionFromTask.txt";
        tree = new ExpressionTree(fileName);

        assertEquals(4, tree.calculate());
    }

    @Test
    void calculateExpressionWithBigNumber() throws IOException { // big means that number is bigger than 9
        fileName = "src/test/resources/expressionWithBigNumber.txt";
        tree = new ExpressionTree(fileName);

        assertEquals(123120, tree.calculate());
    }

    @Test
    void calculateComplicatedExpression() throws IOException {
        fileName = "src/test/resources/complicatedExpression.txt";
        tree = new ExpressionTree(fileName);

        assertEquals(36, tree.calculate());
    }

    @Test
    void calculateManuallyBuiltTree() {
        OperatorNode root = new OperatorNode('+');
        OperandNode leftFirst = new OperandNode(1);
        OperatorNode rightFirst = new OperatorNode('*', new OperandNode(6), new OperandNode(5));
        root.setLeftChild(leftFirst);
        root.setRightChild(rightFirst);
        tree = new ExpressionTree(root);

        assertEquals(31, tree.calculate());
    }
}
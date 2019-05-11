package group144.stepyrev;

import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    private ExpressionTree tree = new ExpressionTree();
    private String fileName = "";

    @Test
    void calculateSimpleExpression() throws IOException {
        fileName = "src/test/resources/simpleExpression.txt";
        tree.getTree(fileName);

        assertEquals(2, tree.calculate());
    }

    @Test
    void calculateExpressionFromTask() throws IOException {
        fileName = "src/test/resources/expressionFromTask.txt";
        tree.getTree(fileName);

        assertEquals(4, tree.calculate());
    }

    @Test
    void calculateExpressionWithBigNumber() throws IOException { // big means that number is bigger than 9
        fileName = "src/test/resources/expressionWithBigNumber.txt";
        tree.getTree(fileName);

        assertEquals(123120, tree.calculate());
    }

    @Test
    void calculateComplicatedExpression() throws IOException {
        fileName = "src/test/resources/complicatedExpression.txt";
        tree.getTree(fileName);

        assertEquals(36, tree.calculate());
    }

}
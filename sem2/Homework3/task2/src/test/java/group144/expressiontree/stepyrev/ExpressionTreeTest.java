package group144.expressiontree.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    @Test
    void calculateSimpleExpression() throws WrongInputException {
        String expression = "(+ 1 1)"; // 1 + 1 = 2
        ExpressionTree expressionTree = new ExpressionTree(expression, 0);

        assertEquals(2, expressionTree.calculate());
    }

    @Test
    void calculateExpressionFromTask() throws WrongInputException {
        String expression = "(* (+ 1 1) 2)"; // (1 + 1) * 2 = 4
        ExpressionTree expressionTree = new ExpressionTree(expression, 0);

        assertEquals(4, expressionTree.calculate());
    }

    @Test
    void calculateExpressionWithBigNumber() throws WrongInputException { // big means that number is bigger than 9
        String expression = "(* 12312 10)"; // 12312 * 10 = 123120
        ExpressionTree expressionTree = new ExpressionTree(expression, 0);

        assertEquals(123120, expressionTree.calculate());
    }

    @Test
    void calculateComplicatedExpression() throws WrongInputException {
        String expression = "(+ (- (* 12 3) 4) (/ (- 25 5) 5))"; // ((12 * 3) - 4) + ((25 - 5) / 5) = 36
        ExpressionTree expressionTree = new ExpressionTree(expression, 0);

        assertEquals(36, expressionTree.calculate());
    }

    @Test
    void calculateWithoutOperation() throws WrongInputException {
        String expression = "(1 1)";
        assertThrows(WrongInputException.class, () -> new ExpressionTree(expression, 0));
    }
}
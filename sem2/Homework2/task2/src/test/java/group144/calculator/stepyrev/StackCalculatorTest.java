package group144.calculator.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackCalculatorTest {
    private static StackCalculator calculator = new StackCalculator();

    @Test
    void calculateAnswerOperationOfAddition() throws WrongInputException {
        String expression = "2 2 +"; // 2 + 2 = 4
        int answer = calculator.calculateAnswer(expression);

        assertEquals(4, answer);
    }

    @Test
    void calculateAnswerOperationOfMultiplication() throws WrongInputException {
        String expression = "2 2 *"; // 2 * 2 = 4
        int answer = calculator.calculateAnswer(expression);

        assertEquals(4, answer);
    }

    @Test
    void calculateAnswerOperationOfSubstraction() throws WrongInputException {
        String expression = "2 2 -"; // 2 - 2 = 0
        int answer = calculator.calculateAnswer(expression);

        assertEquals(0, answer);
    }

    @Test
    void calculateAnswerOperationOfDivision() throws WrongInputException {
        String expression = "2 2 /"; // 2 / 2 = 1
        int answer = calculator.calculateAnswer(expression);

        assertEquals(1, answer);
    }

    @Test
    void calculateAnswerForRegularExpression() throws WrongInputException {
        String expression = "2 2 * 5 - 10 * 2 /"; // (2 * 2 - 5) * 10 / 2 = -5
        int answer = calculator.calculateAnswer(expression);

        assertEquals(-5, answer);
    }

    @Test
    void calculateAnswerForWrongInputDueToLackOfNumber() throws WrongInputException {
        String expression = "2 2 + 5 - 1 - +";

        assertThrows(WrongInputException.class, () -> calculator.calculateAnswer(expression));
    }

    @Test
    void calculateAnswerForWrongInputDueToExtraNumber() {
        String expression = "2 2 + 2 2";

        assertThrows(WrongInputException.class, () -> calculator.calculateAnswer(expression));
    }
}
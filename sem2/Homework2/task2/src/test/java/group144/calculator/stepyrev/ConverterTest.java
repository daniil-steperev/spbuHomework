package group144.calculator.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    private static Converter converter = new Converter();

    @Test
    void convertToPostfixWithoutBrackets() throws EmptyStackException {
        String infixString = "2 + 2 - 5 + 1";
        String postfixString = "2 2 5 1 + - + ";
        StringBuilder convertedString = converter.convertToPostfix(infixString);

        assertEquals(postfixString, convertedString.toString());
    }

    @Test
    void convertToPostfixWithBrackets() throws EmptyStackException {
        String infixString = "((2 - 6) * 5) / 4";
        String postfixString = "2 6 - 5 * 4 / ";
        StringBuilder convertedString = converter.convertToPostfix(infixString);

        assertEquals(postfixString, convertedString.toString());
    }
}
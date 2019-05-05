package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static group144.stepyrev.Controller.calculate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {
    @Test
    public void calculatorSimpleTest() {
        int first = 10;
        int second = 5;

        assertEquals("15.0", calculate(first, second, "+"));
        assertEquals("5.0", calculate(first, second, "-"));
        assertEquals("50.0", calculate(first, second, "*"));
        assertEquals("2.0", calculate(first, second, "/"));
    }

    @Test
    public void calculatorDivisionByZeroTest() {
        int first = 10;
        int second = 0;
        assertEquals("Error! Division by 0!", calculate(first, second, "/"));
    }
}
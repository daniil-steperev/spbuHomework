package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    public void calculateTest() {
        Calculator calculator = new Calculator();
        calculator.initialize("15", "-");

        calculator.calculate("10", "*");
        assertEquals(5.0, calculator.getAnswer(), 0.01); // 15 - 10 = 5

        calculator.calculate("5", "+");
        assertEquals(-35.0, calculator.getAnswer(), 0.01); // 15 - 10 * 5 = -35

        calculator.calculate("5", "/");
        assertEquals(-30.0, calculator.getAnswer(), 0.01); // 15 - 10 * 5 + 5 = -30
    }
}
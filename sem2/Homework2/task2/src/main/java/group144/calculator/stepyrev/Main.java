package group144.calculator.stepyrev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws WrongInputException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter an exception:");

        String expression = reader.readLine();
        Converter converter = new Converter();
        String postfixExpression = converter.convertToPostfix(expression);

        StackCalculator calculator = new StackCalculator();
        int result = calculator.calculateAnswer(postfixExpression);
        System.out.println("The result is: " + result);
    }
}

package group144.calculator.stepyrev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws WrongInputException, IOException, EmptyStackException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Converter converter = new Converter();
        StackCalculator calculator = new StackCalculator();
        int result = 0;

        System.out.println("Enter an exception:");
        String expression = reader.readLine();

        try {
            String postfixExpression = converter.convertToPostfix(expression);
            result = calculator.calculateAnswer(postfixExpression);
        }
        catch (EmptyStackException e) {
            System.out.println("You tried to use method pop() from an empty stack.");
            System.out.println("You entered incorrect expression.");
            System.out.println("Please, make sure " +
                    "that there is an operation for each of number couples, number for " +
                    "each operation; check brackets");
            throw e;
        }
        catch (WrongInputException e) {
            System.out.println("You entered incorrect expression.");
            System.out.println("Please, make sure " +
                    "that there is an operation for each of number couples, number for " +
                    "each operation; check brackets");
            throw e;
        }


        System.out.println("The result is: " + result);
        reader.close();
    }
}

// If the result is not equal what you expected to see, please, make sure that
// you have entered brackets in such order that you want answer to be calculated

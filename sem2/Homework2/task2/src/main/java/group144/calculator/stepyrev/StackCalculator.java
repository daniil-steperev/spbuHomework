package group144.calculator.stepyrev;

/**
 * A class that represents Calculator based on Stack.
 */
public class StackCalculator {
    /**
     * A method that calculates an answer of inputted expression
     * @param postfixExpression means postfix expression that should be calculated
     * @return means calculated result of inputted expression
     * @throws WrongInputException means Exception that should be raised if inputted expression is not correct
     */
    public int calculateAnswer(String postfixExpression) throws WrongInputException {
        ListStack<Integer> stack = new ListStack<Integer>();
        String[] separatedElements = postfixExpression.split(" ");

        for (int i = 0; i < separatedElements.length; i++) {
            if (isNumber(separatedElements[i])) {
                stack.push(Integer.parseInt(separatedElements[i]));
            } else {
                Integer secondNumber = stack.pop();
                Integer firstNumber = stack.pop();
                String operation = separatedElements[i];

                stack.push(makeOperation(firstNumber, secondNumber, operation));
            }
        }

        if (stack.getLength() != 1) {
            throw new WrongInputException("Wrong expression");
        }

        return stack.pop();
    }

    private int makeOperation(Integer firstNumber, Integer secondNumber, String operation) throws WrongInputException {
        if (firstNumber == null || secondNumber == null) {
            throw new WrongInputException("Wrong expression!");
        }

        if (operation.equals("-")) {
            return firstNumber - secondNumber;
        } else if (operation.equals("+")) {
            return firstNumber + secondNumber;
        } else if (operation.equals("*")) {
            return firstNumber * secondNumber;
        } else if (operation.equals("/")) {
            return firstNumber / secondNumber;
        } else {
            throw new WrongInputException("Wrong expression!");
        }
    }

    private boolean isNumber(String element) {
        try {
            int number = Integer.parseInt(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
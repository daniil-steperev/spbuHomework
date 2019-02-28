package group144.calculator.stepyrev;

/**
 * A class that represents Calculator based on Stack.
 */
public class StackCalculator {
    /**
     * A method that calculates an answer of inputted expression
     * @param expression means infix expression that should be calculated
     * @return means calculated result of inputted expression
     * @throws WrongInputException means Exception that should be raised if inputted expression is not correct
     */
    public int calculateAnswer(String expression) throws WrongInputException {
        ListStack<Integer> stack = new ListStack<Integer>();
        String postfixString = convertToPostfix(expression);
        String[] separatedElements = postfixString.split(" ");

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

    private String convertToPostfix(String expression)
    {
        ListStack<String> stack = new ListStack<String>();
        String postfixString = "";
        expression = setSpaces(expression);
        String[] separatedExpression = expression.split(" ");

        for (int i = 0; i < separatedExpression.length; i++)
        {
            if (isNumber(separatedExpression[i]))
            {
                postfixString = postfixString + separatedExpression[i] + " ";
            }
            else if (isOperation(separatedExpression[i]))
            {
                stack.push(separatedExpression[i]);
            }
            else if (separatedExpression[i].equals(")"))
            {
                postfixString = convertFromBrackets(stack, postfixString);
            }
        }
        postfixString =  convertFromStack(stack, postfixString);

        return postfixString;
    }

    private String setSpaces(String expression) {
        String rightExpression = "";
        char[] expressionLetters = expression.toCharArray();
        for (int i = 0; i < expressionLetters.length; i++) {
            if (expressionLetters[i] == ' ') {

            }
            else if (isNumber(expressionLetters[i])) {
                rightExpression = rightExpression + expressionLetters[i];
            }
            else {
                rightExpression = rightExpression + " "  + expressionLetters[i] + " ";
            }
        }

        return rightExpression;
    }

    private String convertFromStack(ListStack<String> stack, String postfixString)
    {
        String element = "";
        while (stack.getLength() > 0)
        {
            element = stack.pop();
            postfixString = postfixString + element + " ";
        }

        return postfixString;
    }

    private String convertFromBrackets(ListStack<String> stack, String postfixString)
    {
        while (true)
        {
            String element = stack.pop();
            if (element.equals("("))
            {
                return postfixString;
            }

            postfixString = postfixString + element + " ";
        }
    }

    private boolean isOperation(String operation)
    {
        return ((operation.equals("(")) || (operation.equals("+")) ||
                (operation.equals("-")) || (operation.equals("*")) ||
                (operation.equals("/")));
    }

    private boolean isNumber(char element) {
        return (element <= '9' && element >= '0');
    }

    private boolean isNumber(String element) {
        try {
            int number = Integer.parseInt(element);
            return true;
        } catch (Exception e) {
            return false;
        }
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
}
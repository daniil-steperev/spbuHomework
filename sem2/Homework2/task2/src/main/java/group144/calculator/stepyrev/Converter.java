package group144.calculator.stepyrev;

/** A class that translates infix expression to postfix expression */
public class Converter {
    /** A method that coverts infix string to postfix form */
    public StringBuilder convertToPostfix(String expression) throws EmptyStackException {
        ListStack<String> stack = new ListStack<>();
        StringBuilder postfixString = new StringBuilder();
        expression = setSpaces(expression);
        String[] separatedExpression = expression.split(" ");

        for (int i = 0; i < separatedExpression.length; i++)
        {
            if (isNumber(separatedExpression[i]))
            {
                postfixString.append(separatedExpression[i] + " ");
            } else if (isOperation(separatedExpression[i])) {
                stack.push(separatedExpression[i]);
            } else if (separatedExpression[i].equals(")")) {
                postfixString = convertFromBrackets(stack, postfixString);
            }
        }
        postfixString =  convertFromStack(stack, postfixString);

        return postfixString;
    }

    private String setSpaces(String expression) {
        StringBuilder rightExpression = new StringBuilder();
        char[] expressionLetters = expression.toCharArray();
        for (int i = 0; i < expressionLetters.length; i++) {
            if (expressionLetters[i] == ' ') {

            } else if (isNumber(expressionLetters[i])) {
                rightExpression.append(expressionLetters[i]);
            } else {
                rightExpression.append(" "  + expressionLetters[i] + " ");
            }
        }

        return rightExpression.toString();
    }

    private StringBuilder convertFromStack(ListStack<String> stack, StringBuilder postfixString) throws EmptyStackException {
        String element = "";
        while (stack.getLength() > 0)
        {
            element = stack.pop();
            postfixString.append(element + " ");
        }

        return postfixString;
    }

    private StringBuilder convertFromBrackets(ListStack<String> stack, StringBuilder postfixString) throws EmptyStackException {
        while (true)
        {
            String element = stack.pop();
            if (element.equals("("))
            {
                return postfixString;
            }

            postfixString.append(element + " ");
        }
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

    private boolean isOperation(String operation)
    {
        return operation.equals("(") || operation.equals("+") ||
                operation.equals("-") || operation.equals("*") ||
                operation.equals("/");
    }
}

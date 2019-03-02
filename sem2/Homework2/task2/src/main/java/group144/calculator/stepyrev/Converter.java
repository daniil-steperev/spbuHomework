package group144.calculator.stepyrev;

/**
 * A class that translates infix expression to postfix expression
 */
public class Converter {
    public String convertToPostfix(String expression)
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
        return ((operation.equals("(")) || (operation.equals("+")) ||
                (operation.equals("-")) || (operation.equals("*")) ||
                (operation.equals("/")));
    }
}

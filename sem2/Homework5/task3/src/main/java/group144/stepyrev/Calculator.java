package group144.stepyrev;

/** A class that represents a calculator with different priority operations. */
public class Calculator {
    /** A last inputted (not first priority) value. */
    private double lastValue;

    /** A last inputted (not first priority, i.e. plus or minus) operation. */
    private String lastOperation;

    /** A first priority value (it would be useful in case of multiplying or dividing. */
    private double priorityValue;

    /** A first priority operation. */
    private String priorityOperation;

    /** A field that indicates whether the calculator was filled. */
    private boolean isEmpty = true;

    /**
     * A method that represents whether the calculator was filled.
     * @return value of field isEmpty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * A method that sets the  priority operation to the calculator.
     * @param operation means new priority operation
     */
    public void setOperation(String operation) {
        priorityOperation = operation;
    }

    /**
     * A method that initializes the calculator.
     * @param value means a new priority value
     * @param operation means a new priority operation
     */
    public void initialize(String value, String operation) {
        priorityValue = Double.parseDouble(value);
        lastOperation = "+";
        priorityOperation = operation;
        isEmpty = false;
    }

    /**
     * A method that calculates current result.
     *
     * If operations were not first priority (i.e, plus or minus) sets new lastValue, priorityValue and priorityOperation.
     * In case of multiplying and dividing counts the result.
     * @param value means a new value
     * @param newOperation means a new operation
     */
    public void calculate(String value, String newOperation) {
        Double newValue = Double.parseDouble(value);
        switch (priorityOperation) {
            case "+":
                if (lastOperation.equals("+")) {
                    lastValue = lastValue + priorityValue;
                } else {
                    lastValue = lastValue - priorityValue;
                }

                priorityValue = newValue;
                lastOperation = priorityOperation;
                break;
            case "-":
                if (lastOperation.equals("+")) {
                    lastValue = lastValue + priorityValue;
                } else {
                    lastValue = lastValue - priorityValue;
                }

                priorityValue = newValue;
                lastOperation = priorityOperation;
                break;
            case "*":
                priorityValue = priorityValue * newValue;
                break;
            case "/":
                priorityValue = priorityValue / newValue;
                break;
        }

        priorityOperation = newOperation;
    }

    /**
     * A method that returns current result.
     *
     * If lastOperation (not priority) was plus, returns sum of lastValue and priorityValue,
     * in case of minus, returns difference between lastValue and priorityValue
     * @return calculated result
     */
    public double getAnswer() {
        switch (lastOperation) {
            case "+":
                return lastValue + priorityValue;
            case "-":
                return lastValue - priorityValue;
        }

        return 0.0;
    }
}

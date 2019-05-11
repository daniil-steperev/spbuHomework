package group144.stepyrev;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import javafx.beans.value.ChangeListener;

/** A class that represents a controller of calculator. */
public class Controller {
    @FXML
    private TextField answer;

    @FXML
    private ChoiceBox<String> operations;

    @FXML
    private Spinner<Integer> firstNumber;

    @FXML
    private Spinner<Integer> secondNumber;

    private ChangeListener changeListener = (observable, oldValue, newValue) -> setNewValues();

    /** A method that initializes text field, choice box and spinners. */
    public void initialize() {
        operations.getItems().addAll("+", "-", "*", "/");
        operations.valueProperty().setValue("-");

        firstNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-1000, 1000, 0, 1));
        secondNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-1000, 1000, 0, 1));

        answer.setText("0");

        firstNumber.valueProperty().addListener(changeListener);
        secondNumber.valueProperty().addListener(changeListener);
        operations.valueProperty().addListener(changeListener);
    }

    private void setNewValues() {
        answer.setText(calculate(firstNumber.getValue(), secondNumber.getValue(), operations.getValue()));
    }

    /**
     * A method that calculates an answer of two numbers and operation.
     *
     * Returns 'Error! Division by 0!' if there is division by 0.
     * @param first - a first integer number
     * @param second - a second integer number
     * @param operation - an operation
     * @return - a value of calculated result
     */
    public static String calculate(int first, int second, String operation) {
        if (operation.equals("/") && second == 0) {
            return "Error! Division by 0!";
        }

        double result = 0;
        switch (operation) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = (double)first / (double)second;
                break;
        }

        return Double.toString(result);
    }
}

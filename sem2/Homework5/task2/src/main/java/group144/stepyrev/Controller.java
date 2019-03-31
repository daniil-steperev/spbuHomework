package group144.stepyrev;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField answer;

    @FXML
    private ChoiceBox<String> operations;

    @FXML
    private Spinner<Integer> firstNumber;

    @FXML
    private Spinner<Integer> secondNumber;

    /** A method that initializes text field, choice box and spinners. */
    public void initialize() {
        operations.getItems().addAll("+", "-", "*", "/");
        operations.valueProperty().setValue("-");

        operations.valueProperty().addListener(((observable, oldValue, newValue) -> calculate()));

        firstNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-1000, 1000, 0, 1));
        secondNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-1000, 1000, 0, 1));

        answer.setText("0");

        firstNumber.valueProperty().addListener(((observable, oldValue, newValue) -> calculate()));
        secondNumber.valueProperty().addListener(((observable, oldValue, newValue) -> calculate()));
    }

    private void calculate() {
        int first = firstNumber.getValue();
        int second = secondNumber.getValue();
        String operation = operations.getValue();

        if (operation.equals("/") && second == 0) {
            answer.setText("Error! Division by 0!");
            return;
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
                result = first / second;
                break;
        }

        answer.setText(Double.toString(result));
    }

}

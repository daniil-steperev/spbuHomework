package group144.stepyrev;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

/** A class that realizes controllers of calculator. */
public class Controller {
    @FXML
    private Button number0;

    @FXML
    private Button number1;

    @FXML
    private Button number2;

    @FXML
    private Button number3;

    @FXML
    private Button number4;

    @FXML
    private Button number5;

    @FXML
    private Button number6;

    @FXML
    private Button number7;

    @FXML
    private Button number8;

    @FXML
    private Button number9;

    @FXML
    private Button buttonPlus;

    @FXML
    private Button buttonMinus;

    @FXML
    private Button buttonMultiply;

    @FXML
    private Button buttonDivide;

    @FXML
    private Button buttonEqual;

    @FXML
    private TextField answer;

    @FXML
    private TextField buffer;

    @FXML
    private Button buttonAC;

    private boolean isNewNumber = true;

    private Calculator calculator = new Calculator();

    private Button[] numberButtons;

    private Button[] operationButton;

    /** A method that initializes buttons and text fields. */
    public void initialize() {
        numberButtons = new Button[]{number0, number1, number2, number3, number4, number5, number6,
                number7, number8, number9};
        operationButton = new Button[]{buttonEqual, buttonDivide, buttonMultiply, buttonMinus, buttonPlus};
        answer.setText("0");
    }

    /**
     * A method that is called when a number button was pressed.
     * @param actionEvent an event which led to a button click
     */
    @FXML
    public void pressNumberButton(ActionEvent actionEvent) {
        if (isNewNumber) {
            answer.setText("0");
            isNewNumber = false;
        }

        for (int i = 0; i < 10; i++) {
            if (actionEvent.getSource().equals(numberButtons[i])) {
                StringBuilder tmp = new StringBuilder();

                if (!answer.getText().equals("0")) {
                    tmp.append(answer.getText());
                }

                tmp.append(i);
                answer.setText(tmp.toString());
            }
        }

        unlockOperations();
    }

    /**
     * A method that is called when a number button was pressed.
     * @param actionEvent an event which led to a button click
     */
    @FXML
    public void pressOperationButton(ActionEvent actionEvent) {
        String operation = "";
        for (int i = 0; i < operationButton.length; i++) {
            if (actionEvent.getSource().equals(operationButton[i])) {
                operation = operationButton[i].getText();
            }
        }

        if (operation.equals("=")) {
            pressEqualButton();
            return;
        }

        if (isNewNumber) {
            String tmp = buffer.getText();
            if (!tmp.equals("")) {
                String newTmp = tmp.substring(0, tmp.length() - 2);
                StringBuilder newBuffer = new StringBuilder(newTmp);
                newBuffer.append(operation + " ");
                buffer.setText(newBuffer.toString());
            }

            calculator.setOperation(operation);
            return;
        }

        updateBuffer(" " + operation + " ");

        if (calculator.isEmpty()) {
            calculator.initialize(answer.getText(), operation);
        } else {
            calculator.calculate(answer.getText(), operation);
        }

        answer.setText(String.valueOf(calculator.getAnswer()));
        isNewNumber = true;

        lockOperations();
    }


    /** A method that is called when button "=" was pressed.
     *
     * Updates buffer, answer fields.
     * Initializes calculator if it was not.
     */
    @FXML
    public void pressEqualButton() {
        buffer.setText("");
        if (isNewNumber) {
            return;
        }

        isNewNumber = true;

        if (calculator.isEmpty()) {
            return;
        }

        calculator.calculate(answer.getText(), "+");
        answer.setText(String.valueOf(calculator.getAnswer()));
        calculator = new Calculator();
    }

    /** A method that is called when button "AC" was pressed.
     *
     * Updates buffer, answer fields.
     * Initializes calculator if it was not.
     */
    @FXML
    public void pressCleanAllButton() {
        answer.setText("0");
        buffer.setText("");
        calculator = new Calculator();
    }

    /**
     * A method that updates current buffer.
     * @param newOperation means a new operation that should be added.
     */
    private void updateBuffer(String newOperation) {
        StringBuilder newBuffer = new StringBuilder(buffer.getText());
        newBuffer.append(answer.getText());
        newBuffer.append(newOperation);
        buffer.setText(newBuffer.toString());
    }

    /**
     * A method that locks to user operation buttons.
     *
     * This method is useful in case of inputted operation (i.e. after operation should be a number).
     */
    private void lockOperations() {
        buttonPlus.setDisable(true);
        buttonMinus.setDisable(true);
        buttonMultiply.setDisable(true);
        buttonDivide.setDisable(true);
        buttonEqual.setDisable(true);
    }

    /**
     * A method that unlocks to user operation buttons.
     *
     * This method is useful in case of inputted number (i.e. after number should be an operation).
     */
    private void unlockOperations() {
        buttonPlus.setDisable(false);
        buttonMinus.setDisable(false);
        buttonMultiply.setDisable(false);
        buttonDivide.setDisable(false);
        buttonEqual.setDisable(false);
    }
}

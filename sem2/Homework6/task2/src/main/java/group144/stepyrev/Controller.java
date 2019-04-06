package group144.stepyrev;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

/** A class that interacts with the user and program. */
public class Controller {
    @FXML
    private Button buttonOne;

    @FXML
    private Button buttonTwo;

    @FXML
    private Button buttonThree;

    @FXML
    private Button buttonFour;

    @FXML
    private Button buttonFive;

    @FXML
    private Button buttonSix;

    @FXML
    private Button buttonSeven;

    @FXML
    private Button buttonEight;

    @FXML
    private Button buttonNine;

    @FXML Button buttonNewGame;

    @FXML
    private TextField text;

    private Button[] buttons;

    private TicTacToeBoard board =  new TicTacToeBoard();

    /** A method that initializes the controller. */
    public void initialize() {
        buttons = new Button[]{buttonOne, buttonTwo, buttonThree,
                               buttonFour, buttonFive, buttonSix,
                               buttonSeven, buttonEight, buttonNine};

        text.setText("First player turn");
    }

    /**
     * A method that is called when button(0 - 9) is pressed.
     * @param actionEvent an event to understand what button was pressed
     */
    @FXML
    public void pressButton(ActionEvent actionEvent) {
        for (int i = 0 ; i < buttons.length; i++) {
            if (actionEvent.getSource().equals(buttons[i])) {
                board.makeMove(i);
                buttons[i].setText(String.valueOf(board.returnCurrentPlayer()));
                buttons[i].setDisable(true);
            }
        }

        if (board.isAllFilled()) {
            if (board.checkWinner()) {
                if (board.returnCurrentPlayer() == 'X') {
                    text.setText("First player won!");
                } else {
                    text.setText("Second player won!");
                }
            }
            text.setText("Draw!");
            return;
        }

        if (board.checkWinner()) {
            char winner = board.returnCurrentPlayer();
            if (winner == 'X') {
                text.setText("First player won!");
            } else {
                text.setText("Second player won!");
            }

            lockAllButtons();
            return;
        }

        board.changeCurrentPlayer();
        if (board.returnCurrentPlayer() == 'X') {
            text.setText("First player turn");
        } else {
            text.setText("Second player turn");
        }
    }

    /** A method that starts a new game. */
    @FXML
    private void startNewGame() {
        board = new TicTacToeBoard();
        unlockAllButtons();
        text.setText("First player turn");
        cleanButtonsText();
    }

    /** A method that locks all buttons. */
    private void lockAllButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setDisable(true);
        }
    }

    /** A method that unlocks all buttons. */
    private void unlockAllButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setDisable(false);
        }
    }

    /** A method that cleans a text from all buttons. */
    private void cleanButtonsText() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
    }
}

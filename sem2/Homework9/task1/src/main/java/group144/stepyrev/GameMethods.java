package group144.stepyrev;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

/** A class that represents methods which describes a work of the game. */
public class GameMethods {
    /** A button that starts a new game. */
    private Button start;

    /** A text field where the data about the game is published. */
    private TextField gameInfoField;

    /** A grid pane to get buttons. */
    private GridPane gameParent;
    private Button[] buttons;

    private TicTacToeBoard board;
    private int size;

    /** A stream where a data about the game should be published. */
    private InputStream inStream;
    /** A stream from where a data about the game should be got. */
    private PrintStream outStream;

    /** An indicator of a new game. */
    private boolean isNewGame = false;
    /** An indicator of a current game (if player left, it would be false). */
    private boolean isGamePlaying = true;

    /** An indicator of current user (client or server). */
    private String user;

    /**
     * A constructor of the game class.
     * @param parent - a parent
     * @param start - a button start
     * @param field - a field with information about the game
     * @param user - a current user
     */
    public GameMethods(GridPane parent, Button start, TextField field, String user) {
        gameParent = parent;
        this.start = start;
        gameInfoField = field;
        this.user = user;
        this.board = new TicTacToeBoard();
        size = board.getSize();

        buttons = new Button[size];
        for (int i = 0; i < size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }
    }

    /**
     * A method that sets the streams.
     * @param in - an input stream
     * @param out - an output stream
     */
    public void setStreams(InputStream in, PrintStream out) {
        inStream = in;
        outStream = out;
    }

    /**
     * A method that returns field isGamePlaying.
     * @return - a field isGamePlaying
     */
    public boolean getIsGamePlaying() {
        return isGamePlaying;
    }

    /**
     * A method that is called when cell button was pressed.
     * @param event - an event that occurred
     */
    public void pressCellButton(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            if (buttons[i].equals(event.getSource())) {
                if (board.makeMove(i)) {
                    board.makeMove(i);
                    buttons[i].setDisable(true);
                    buttons[i].setText(board.getCurrentPlayer());

                    outStream.print(i);
                    outStream.flush();

                    if (user.equals("client")) {
                        gameInfoField.setText("Wait for opponent's turn");
                        lockAllButtons();

                        if (board.getGameStatus() == TicTacToeBoard.GameStatus.PLAYING) {
                            waitOpponentTurn();
                        }
                    } else {
                        if (board.getGameStatus() == TicTacToeBoard.GameStatus.PLAYING) {
                            waitOpponentTurn();
                        }

                        gameInfoField.setText("Wait for opponent's turn");
                        lockAllButtons();
                    }

                    checkWinner();
                }
            }
        }
    }

    /** A method that locks all buttons. */
    public void lockAllButtons() {
        for (int i = 0; i < size; i++) {
            gameParent.getChildren().get(i).setDisable(true);
            start.setDisable(true);
        }
    }

    /** A method that unlocks all buttons. */
    public void unlockAllButtons() {
        for (int i = 0; i < size; i++) {
            gameParent.getChildren().get(i).setDisable(false);
            start.setDisable(false);
        }
    }

    /** A method that waits opponent turn.
     *
     *  A thread is used here to make checking a turn more optimised.
     */
    public void waitOpponentTurn() {
        new Thread(() -> {
            int position = 'x';
            while (true) {
                try {
                    if (inStream.available() == 0) {
                        try {
                            position = toNumber(inStream.read());
                        } catch (SocketException e) {
                            isGamePlaying = false;
                        }

                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            int finalPosition = position;
            Platform.runLater(() -> getOpponentTurn(finalPosition));
        }).start();
    }

    private int toNumber(int element) {
        return element - '0';
    }

    /** A method that gets an opponent turn.
     *
     *  If the opponent has left the game, current player is considered as winner.
     */
    private void getOpponentTurn(int position) {
        if (position == 9) {
            isNewGame = true;
            startNewGame();
        } else if (position >= 0 && position <= 8) {
            board.makeMove(position);
            buttons[position].setText(board.getCurrentPlayer());

            gameInfoField.setText("Your turn, my lord");
            unlockAllButtons();
            checkWinner();
        } else {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Game over");
            message.setContentText("Your opponent left the game. My lord, you are winner!");
            message.showAndWait();
            isGamePlaying = false;
            Platform.exit();
        }
    }

    /** A method that starts a new game. */
    public void startNewGame() {
        if (!isNewGame) {
            outStream.print(9);
            outStream.flush();
        }

        board = new TicTacToeBoard();
        for (int i = 0; i < size; i++) {
            buttons[i].setText("");
        }
        isNewGame = false;

        if (user.equals("client")) {
            gameInfoField.setText("Wait for opponent's turn");
            lockAllButtons();
            waitOpponentTurn();
        } else {
            gameInfoField.setText("Your turn, my lord");
            unlockAllButtons();
        }
    }

    /** A method that checks what is the result of the game. */
    private void checkWinner() {
        if (board.getGameStatus() == TicTacToeBoard.GameStatus.WIN) {
            StringBuilder message = new StringBuilder("Won of ");
            message.append(board.getCurrentPlayer());
            message.append(" player!");
            showEndOfGame(message.toString());
        } else if (board.getGameStatus() == TicTacToeBoard.GameStatus.DRAW) {
            String message = "Draw!";
            showEndOfGame(message);
        }
    }

    /** A method that shows the end of the game. */
    private void showEndOfGame(String message) {
        isNewGame = true;

        Alert gameMessage = new Alert(Alert.AlertType.INFORMATION);
        gameMessage.setTitle("Game over!");
        gameMessage.setHeaderText("Game over!");
        gameMessage.setContentText(message);

        gameMessage.showAndWait();
        lockAllButtons();
        start.setDisable(false);
    }
}

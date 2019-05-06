package group144.stepyrev;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/** A class that represents a server controller. */
public class ServerController {
    /** An array with board buttons. */
    @FXML
    private Button[] buttons;

    /** A button that starts a new game. */
    @FXML
    private Button startButton;

    /** A text field where the data about the game is published. */
    @FXML
    private TextField gameInfo;

    /** A grid pane to get buttons. */
    @FXML
    private GridPane parent;

    private Socket client;

    private static final int portID = 11111;

    private TicTacToeBoard board;
    private int size;

    /** A stream where a data about the game should be published. */
    private InputStream in;
    /** A stream from where a data about the game should be got. */
    private PrintStream out;

    /** An indicator of a new game. */
    private boolean isNewGame = false;
    /** An indicator of a current game (if player left, it would be false). */
    private boolean isGamePlaying = true;

    /** A method that initializes a controller. */
    public void initialize() {
        board = new TicTacToeBoard();
        size = board.getSize();

        buttons = new Button[size];
        for (int i = 0; i < size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }
        lockAllButtons();

        try {
            ServerSocket socket = new ServerSocket(portID);

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Click here to start a server.");
            message.showAndWait();

            client = socket.accept();
            out = new PrintStream(client.getOutputStream());
            in = client.getInputStream();

            gameInfo.setText("Your turn, my lord");
            unlockAllButtons();
        } catch (IOException e) {
        }
    }

    /** A method that is called when game button is pressed. */
    @FXML
    private void pressButton(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            if (buttons[i].equals(event.getSource())) {
                if (board.makeMove(i)) {
                    board.makeMove(i);
                    buttons[i].setDisable(true);
                    buttons[i].setText(board.getCurrentPlayer());

                    out.print(i);
                    out.flush();

                    if (board.getGameStatus() == TicTacToeBoard.GameStatus.PLAYING) {
                        waitOpponentTurn();
                    }

                    gameInfo.setText("Waiting for opponent's turn");
                    lockAllButtons();

                    checkWinner();
                }
            }
        }
    }

    private void lockAllButtons() {
        for (int i = 0; i < size; i++) {
            parent.getChildren().get(i).setDisable(true);
        }
    }

    private void unlockAllButtons() {
        for (int i = 0; i < size; i++) {
            parent.getChildren().get(i).setDisable(false);
        }
    }

    /** A method that waits opponent turn.
     *
     *  A thread is used here to make checking a turn more optimised.
     */
    private void waitOpponentTurn() {
        new Thread(() -> {
            int position = 'x';
            while (true) {
                try {
                    if (in.available() == 0) {
                        try {
                            position = toNumber(in.read());
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

    /** A method that gets an opponent turn.
     *
     *  If the opponent has left the game, current player is considered as winner.
     */
    private void getOpponentTurn(int position) {
        if (position == 9) {
            isNewGame = true;
            newGame();
        } else if (position >= 0 && position <= 8) {
            board.makeMove(position);
            buttons[position].setText(board.getCurrentPlayer());

            gameInfo.setText("Your turn, my lord");
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

    private int toNumber(int element) {
        return element - '0';
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
        newGame();
    }

    /**
     * A method that return current isGamePlaying.
     * @return - isGamePlaying field
     */
    public boolean getIsGamePlaying() {
        return isGamePlaying;
    }

    /** A method that sends an exit message to application. */
    public void sentExitMessage() {
        out.print(-1);
        out.flush();
    }

    /** A method that closes current client. */
    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newGame() {
        if (!isNewGame) {
            out.print(9);
            out.flush();
        }

        board = new TicTacToeBoard();
        for (int i = 0; i < size; i++) {
            buttons[i].setText("");
        }

        gameInfo.setText("Your turn, my lord");
        unlockAllButtons();
        isNewGame = false;
    }
}

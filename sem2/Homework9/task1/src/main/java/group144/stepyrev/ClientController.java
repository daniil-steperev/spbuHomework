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
import java.net.Socket;
import java.net.SocketException;

public class ClientController {
    @FXML
    private Button[] buttons;

    @FXML
    private Button startButton;

    @FXML
    private TextField gameInfo;

    @FXML
    private GridPane parent;

    private Socket client;

    private static final int portID = 11111;

    private TicTacToeBoard board;
    private int size;

    private InputStream in;
    private PrintStream out;

    private boolean isNewGame = false;
    private boolean isEndOfGame = true;

    public void initialize() {
        board = new TicTacToeBoard();
        size = board.getSize();

        buttons = new Button[size];
        for (int i = 0; i < size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("Click here to start connection");
        message.showAndWait();

        while (true) {
            try {
                client = new Socket("local", portID);
                in = client.getInputStream();
                out = new PrintStream(client.getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lockAllButtons();
        gameInfo.setText("Waiting for opponent's turn");
        waitOpponentTurn();
    }

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

    private void waitOpponentTurn() {
        new Thread(() -> {
            int position = 'x';
            while (true) {
                try {
                    if (in.available() == 0) {
                        try {
                            position = toNumber(in.read());
                        } catch (SocketException e) {
                            isEndOfGame = false;
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
            message.setTitle("Your opponent left the game. My lord, you are winner!");
            message.showAndWait();
            isEndOfGame = false;
            Platform.exit();
        }
    }

    private int toNumber(int element) {
        return element - '0';
    }

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

    private void showEndOfGame(String message) {
        isNewGame = true;

        Alert gameMessage = new Alert(Alert.AlertType.INFORMATION);
        gameMessage.setTitle("Game over!");
        gameMessage.setHeaderText("Game over!");
        gameMessage.setContentText(message);

        gameMessage.showAndWait();
        newGame();
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

    public boolean getIsEndOfGame() {
        return isEndOfGame;
    }

    public void sentExitMessage() {
        out.print(-1);
        out.flush();
    }

    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

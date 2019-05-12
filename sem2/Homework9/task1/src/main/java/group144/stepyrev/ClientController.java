package group144.stepyrev;

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

/** A class that represents a client controller. */
public class ClientController {
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

    private InputStream in;
    private PrintStream out;

    private GameMethods game;

    /** A method that initializes the client. */
    public void initialize() {
        game = new GameMethods(parent, startButton, gameInfo, "client");

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("Click here to connect to the server");
        message.showAndWait();

        while (true) {
            try {
                client = new Socket("localhost", portID);
                in = client.getInputStream();
                out = new PrintStream(client.getOutputStream());
                game.setStreams(in, out);
                break;
            } catch (IOException e) {
            }
        }

        game.lockAllButtons();
        gameInfo.setText("Waiting for opponent's turn");
        game.waitOpponentTurn();
    }

    /** A method that is called when game button is pressed. */
    @FXML
    private void pressButton(ActionEvent event) {
        game.pressCellButton(event);
    }

    @FXML
    private void newGame() {
        game.startNewGame();
    }

    /**
     * A method that return current isGamePlaying.
     * @return - isGamePlaying field
     */
    public boolean getGamePlaying() {
        return game.getIsGamePlaying();
    }

    /** A method that sends an exit message to application. */
    public void sendExitMessage() {
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
}

package group144.stepyrev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/** A class that represents a server controller. */
public class ServerController {
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

    private GameMethods game;

    private InputStream in;
    private PrintStream out;

    /** A method that initializes a controller. */
    public void initialize() {
        game = new GameMethods(parent, startButton, gameInfo, "server");
        game.lockAllButtons();

        try {
            ServerSocket socket = new ServerSocket(portID);

            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Click here to start a server.");
            message.showAndWait();

            client = socket.accept();
            out = new PrintStream(client.getOutputStream());
            in = client.getInputStream();
            game.setStreams(in, out);

            gameInfo.setText("Your turn, my lord");
            game.unlockAllButtons();
        } catch (IOException e) {
        }
    }

    /** A method that is called when game button is pressed. */
    @FXML
    private void pressButton(ActionEvent event) {
        game.pressCellButton(event);
    }

    /**
     * A method that return current isGamePlaying.
     * @return - isGamePlaying field
     */
    public boolean getIsGamePlaying() {
        return game.getIsGamePlaying();
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
        game.startNewGame();
    }
}

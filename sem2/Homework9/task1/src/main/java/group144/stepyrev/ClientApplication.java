package group144.stepyrev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** A class that represents a client application. */
public class ClientApplication extends Application {
    private ClientController controller;

    public static void main(String[] args) {
        launch(args);
    }

    /** A method that starts a client application. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-tac-toe client");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(350);

        primaryStage.show();
    }

    /** A method that stops a client application. */
    @Override
    public void stop() {
        if (controller.getGamePlaying()) {
            controller.sendExitMessage();
        } else {
            controller.closeConnection();
        }

        try {
            super.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

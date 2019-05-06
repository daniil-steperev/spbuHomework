package group144.stepyrev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** A class that represents a server application. */
public class ServerApplication extends Application {
    private ServerController controller;

    public static void main(String[] args) {
        launch(args);
    }

    /** A method that starts a server application. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("server.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-tac-toe server");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(350);

        primaryStage.show();
    }

    /** A method that stops a server application. */
    @Override
    public void stop() {
        if (controller.getIsGamePlaying()) {
            controller.sentExitMessage();
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

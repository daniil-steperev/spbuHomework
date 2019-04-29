package group144.stepyrev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerApplication extends Application {
    private ServerController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("server.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-tac-toe server");

        primaryStage.show();
    }

    @Override
    public void stop() {
        if (controller.getIsEndOfGame()) {
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

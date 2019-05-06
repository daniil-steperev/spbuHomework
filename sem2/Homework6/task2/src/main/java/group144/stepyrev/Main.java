package group144.stepyrev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tic tac toe");
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(400);
            primaryStage.setMinHeight(300);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

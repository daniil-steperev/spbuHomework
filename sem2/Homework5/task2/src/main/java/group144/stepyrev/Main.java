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
            primaryStage.setTitle("Simple calculator");
            primaryStage.setScene(new Scene(root, 400, 200));

            primaryStage.setMinHeight(100);
            primaryStage.setMinWidth(300);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package group144.stepyrev;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

public class Controller {

    @FXML
    private Slider slider;

    @FXML
    private ProgressBar progressBar;

    /** A method that sets the value of slider to the progress bar. */
    public void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> progressBar.progressProperty().setValue(newValue));
    }
}

package quantity.glasswindow.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController extends TransitionController{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        this.transition("Login.fxml", event);
    }
}
package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SignUpController extends TransitionController{
    @FXML
    protected void onSignUpButton(ActionEvent event) throws IOException {
        this.transition("General View.fxml", event);
    }
    @FXML
    protected void onLoginLink(ActionEvent event) throws IOException {
        this.transition("Login.fxml", event);
    }
}

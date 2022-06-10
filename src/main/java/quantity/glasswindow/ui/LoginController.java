package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController extends TransitionController {
    @FXML
    protected void onLoginButton(ActionEvent event) throws IOException {
        this.transition("main_view.fxml", event);
    }
    @FXML
    protected void onSignUpLink(ActionEvent event) throws IOException {
        this.transition("Basic Sign Up.fxml", event);
    }
}

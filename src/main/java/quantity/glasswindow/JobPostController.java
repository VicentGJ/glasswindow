package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class JobPostController extends TransitionController{
    @FXML
    protected void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onApplyButton(ActionEvent event) throws IOException {
        this.transition("JobPostController.fxml", event);
    }
}

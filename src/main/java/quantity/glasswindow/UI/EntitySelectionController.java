package quantity.glasswindow.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EntitySelectionController extends TransitionController{
    @FXML
    protected void onCandidateButton(ActionEvent event) throws IOException {
        this.transition("Job Post List.fxml", event);
    }
    @FXML
    protected void onCompanyButton(ActionEvent event) throws IOException {
        this.transition("General View.fxml", event);
    }
}

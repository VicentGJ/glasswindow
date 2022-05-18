package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class GeneralController extends TransitionController {
    @FXML
    protected void onCompanyLink(ActionEvent event) throws IOException {
        this.transition("Company view.fxml", event);
    }
    @FXML
    protected void onCandidateLink(ActionEvent event) throws IOException {
        this.transition("Candidate view.fxml", event);
    }
}

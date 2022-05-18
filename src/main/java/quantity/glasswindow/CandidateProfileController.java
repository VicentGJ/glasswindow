package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CandidateProfileController extends TransitionController{
    @FXML
    protected void onBackButton(ActionEvent event) throws IOException {
        this.transition("Job Post List.fxml", event);
    }
    @FXML
    protected void onEditButton(ActionEvent event) throws IOException{
        this.transition("Candidate Edit.fxml", event);
    }
}

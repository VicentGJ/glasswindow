package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CandidateProfileEditController extends TransitionController{
    @FXML
    protected void onSaveButton(ActionEvent event) throws IOException{
        this.transition("Candidate View.fxml", event);
    }
    @FXML
    protected void onBackButton(ActionEvent event) throws IOException{
        this.transition("Candidate View.fxml", event);
    }
}

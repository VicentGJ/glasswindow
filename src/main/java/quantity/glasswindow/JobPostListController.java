package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class JobPostListController extends TransitionController{
    @FXML
    protected void onProfileButton(ActionEvent event) throws IOException{
        this.transition("Candidate View.fxml", event);
    }
}

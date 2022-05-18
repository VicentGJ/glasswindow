package quantity.glasswindow.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CompanyEditController extends TransitionController{
    @FXML
    protected void onButtonBack(ActionEvent event) throws IOException{
        this.transition("Company View", event);
    }
}

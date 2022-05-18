package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CompanyViewController extends TransitionController{
    @FXML
    protected void onBackButton(ActionEvent event) throws IOException{
        this.transition("General View.fxml", event);
    }
    @FXML
    protected void onEditButton(ActionEvent event) throws  IOException{
        this.transition("Company Edit.fxml", event);
    }
}

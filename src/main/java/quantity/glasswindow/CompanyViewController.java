package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class CompanyViewController extends TransitionController{
    @FXML
    protected void onBackButton(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onEditButton(ActionEvent event) throws  IOException{
        this.transition("Company Edit.fxml", event);
    }
}

package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class TransitionController {

    @FXML
    protected void transition(String fxmlResource, ActionEvent event) throws IOException {
        Parent next_page_parent = FXMLLoader.load(getClass().getResource(fxmlResource));
        Scene next_page_scene = new Scene(next_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(next_page_scene);
        app_stage.show();
    }
}

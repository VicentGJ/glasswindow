package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public class MainJobPostsController extends MainController {
    @FXML
    protected void onEntityLink(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Job Post View.fxml"), "Candidate Properties", null);
    }

    @FXML
    protected void onEditButton(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Job Post Edit.fxml"), "Candidate Edit", null);
    }

    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {

    }

    @FXML
    protected void onSearchButton() throws  IOException {

    }
}

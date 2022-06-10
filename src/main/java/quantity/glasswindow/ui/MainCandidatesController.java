package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public class MainCandidatesController extends MainController {
    @Override
    protected void onEntityLink(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Candidate View.fxml"), "Candidate Properties", null);
    }

    @Override
    protected void onEditButton(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Candidate Edit.fxml"), "Candidate Edit", null);
    }

    @Override
    protected void onDeleteButton(ActionEvent event) throws IOException {

    }

    @Override
    protected void onSearchButton() throws  IOException {

    }
}

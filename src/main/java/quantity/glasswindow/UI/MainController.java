package quantity.glasswindow.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class MainController extends TransitionController{
    @FXML
    protected void onCompaniesSection(ActionEvent event) throws IOException {
        this.transition("main_companies", event);
    }
    @FXML
    protected void onCandidatesSection(ActionEvent event) throws IOException {
        this.transition("main_candidates", event);
    }
    @FXML
    protected void onJobPotsSection(ActionEvent event) throws IOException {
        this.transition("main_job_posts", event);
    }
    @FXML
    protected abstract void onEntityLink(ActionEvent event) throws IOException;

    @FXML
    protected abstract void onEditButton(ActionEvent event) throws IOException;

    @FXML
    protected abstract void onDeleteButton(ActionEvent event) throws IOException;

    @FXML
    protected abstract void onSearchButton() throws  IOException;
}

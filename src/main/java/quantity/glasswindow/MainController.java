package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class MainController extends TransitionController{
    @FXML
    protected void onCompaniesSection(ActionEvent event) throws IOException {
        this.transition("main_companies.fxml", event);
    }
    @FXML
    protected void onCandidatesSection(ActionEvent event) throws IOException {
        this.transition("main_candidates.fxml", event);
    }
    @FXML
    protected void onJobPotsSection(ActionEvent event) throws IOException {
        this.transition("main_job_posts.fxml", event);
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

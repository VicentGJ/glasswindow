package quantity.glasswindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public abstract class MainController extends TransitionController{
    @FXML
    protected void onNewCompany(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Company Edit.fxml"), "Edit Company", null);
    }
    @FXML
    protected void onNewCandidate(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Candidate Edit.fxml"), "Edit Company", null);
    }
    @FXML
    protected void onNewJobPost(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Job Post Edit.fxml"), "Edit Company", null);
    }
    @FXML
    protected void onQuit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.exit(0);
    }
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

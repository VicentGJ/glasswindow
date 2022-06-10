package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public class MainController extends TransitionController{
    

    @FXML
    protected void onNewCompany(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Company Edit.fxml"), "New Company", null);
    }
    @FXML
    protected void onNewCandidate(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Candidate Edit.fxml"), "New Candidate", null);
    }
    @FXML
    protected void onNewJobPost(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Job Post Edit.fxml"), "New Job Post", null);
    }
    @FXML
    protected void onQuit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    protected void onProfileLink(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Profile View.fxml"), "User Profile", null);
    }
    @FXML
    protected void onCompaniesSection(ActionEvent event) throws IOException {
        
    }
    @FXML
    protected void onCandidatesSection(ActionEvent event) throws IOException {
        
    }
    @FXML
    protected void onJobPotsSection(ActionEvent event) throws IOException {
        
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

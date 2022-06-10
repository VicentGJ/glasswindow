package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Model;
import quantity.glasswindow.core.OrderBy;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController extends TransitionController implements Initializable {

    @FXML
    private ListView<String> mainList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Agency agency = Agency.create();
        try {
            ArrayList<Model> list = new ArrayList<>(agency.getObjectList("Company", OrderBy.ID, new HashMap<>()));

            ArrayList<String> ids = new ArrayList<>();
            for (Model i: list) {
                ids.add(i.getId());
            }
            mainList.setItems(FXCollections.observableArrayList(ids));
            System.out.println(this.mainList.getItems());
        }
        catch (Exception e) {
            System.out.println("Lola");
        }
    }

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
    protected void onEntityLink(ActionEvent event) throws IOException {}

    @FXML
    protected void onEditButton(ActionEvent event) throws IOException {}

    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {}

    @FXML
    protected void onSearchButton() throws  IOException {}
}

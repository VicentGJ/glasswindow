package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController extends TransitionController implements Initializable {

    @FXML
    private ListView<String> mainList;
    private Agency agency;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        try {
            ArrayList<String> list = new ArrayList<>(agency.gerObjectListIDs("Company", new HashMap<>()));
            mainList.setItems(FXCollections.observableArrayList(list));
            System.out.println(this.mainList.getItems());
        }
        catch (Exception e) {
            System.out.println("Lola");
        }
    }

    public void updateListView() {
        System.out.println("lila");
    }

    @FXML
    protected void onNewCompany(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Company New.fxml"), "New Company", null);
    }
    @FXML
    protected void onNewCandidate(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Candidate Edit.fxml"), "New Candidate", null);
    }
    @FXML
    protected void onNewJobPost(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Job Post Edit.fxml"), "New Job Post", null);
    }
    @FXML
    protected void onQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void onCompaniesSection(ActionEvent event) throws IOException {
        
    }
    @FXML
    protected void onCandidatesSection(ActionEvent event) throws IOException {
        ViewLoader.thisWindow(getClass().getResource("Calendar View.fxml"), event);
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

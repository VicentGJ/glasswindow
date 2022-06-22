package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public class MainController extends TransitionController implements Initializable {

    @FXML
    private ListView<String> mainList;
    private Agency agency;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.create();
        try {
            this.agency.initTestData();
            ArrayList<String> list = new ArrayList<>(agency.gerObjectListIDs("Company", OrderBy.ID, new HashMap<>()));
            mainList.setItems(FXCollections.observableArrayList(list));
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
    protected void onEntityLink(ActionEvent event) throws IOException {}

    @FXML
    protected void onEditButton(ActionEvent event) throws IOException {}

    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {}

    @FXML
    protected void onSearchButton() throws  IOException {}
}

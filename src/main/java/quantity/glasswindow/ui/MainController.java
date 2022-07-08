package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.IListener;
import quantity.glasswindow.core.Model;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.InvalidTypeException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends TransitionController implements Initializable, IListener {

    @FXML
    private ListView<Model> mainList;
    private Agency agency;

    private ObservableList<String> mainListItems = FXCollections.observableArrayList();

    private String state;

    @Override
    public void update() {
        try {
            mainList.setItems(agency.getActiveModels(state));
        } catch (InvalidTypeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        agency.subscribe(this);
        try {
            mainList.setItems(agency.getActiveModels("Company"));
        } catch (InvalidTypeException e) {
            throw new RuntimeException(e);
        }
        state = "Company";
        // Try to modify this in the future to work with Model abstract class instead of multiple concrete classes
        mainList.setCellFactory(param -> {
            ListCell<Model> cell = new ListCell<Model>() {
                @Override
                protected void updateItem(Model item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getId() == null) {
                        setText(null);
                    } else {
                        setText(item.getId());
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Model c = cell.getItem();
                    try {
                        CompanyViewController controller = (CompanyViewController) ViewLoader.newWindow(
                                getClass().getResource("Company View.fxml"), c.getId(), null
                        );
                        controller.loadViewInfo(c.getId());
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
        System.out.println(this.mainList.getItems());
    }

    @FXML
    protected void onNewCompany(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Company New.fxml"), "New Company", null);
    }
    @FXML
    protected void onNewCandidate(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Candidate New.fxml"), "New Candidate", null);
    }
    @FXML
    protected void onNewJobPost(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Job Post New.fxml"), "New Job Post", null);
    }
    @FXML
    protected void onQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void onCompaniesSection(ActionEvent event) throws IOException, InvalidTypeException {
        state = "Company";
        mainList.setItems(null);
        mainList.setItems(agency.getActiveModels("Company"));
        mainList.setCellFactory(param -> {
            ListCell<Model> cell = new ListCell<Model>() {
                @Override
                protected void updateItem(Model item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getId() == null) {
                        setText(null);
                    } else {
                        setText(item.getId());
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Model c = cell.getItem();
                    try {
                        CompanyViewController controller = (CompanyViewController) ViewLoader.newWindow(
                                getClass().getResource("Company View.fxml"), c.getId(), null
                        );
                        controller.loadViewInfo(c.getId());
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
        System.out.println(this.mainList.getItems());


    }
    @FXML
    protected void onCandidatesSection(ActionEvent event) throws InvalidTypeException {
        state = "Candidate";
        mainList.setItems(null);
        mainList.setItems(agency.getActiveModels("Candidate"));
        mainList.setCellFactory(param -> {
            ListCell<Model> cell = new ListCell<Model>() {
                @Override
                protected void updateItem(Model item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getId() == null) {
                        setText(null);
                    } else {
                        setText(item.getId());
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Model c = cell.getItem();
                    try {
                        CandidateProfileController controller = (CandidateProfileController) ViewLoader.newWindow(
                                getClass().getResource("Candidate View.fxml"), c.getId(), null
                        );
                        controller.loadViewInfo(c.getId());
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
        System.out.println(this.mainList.getItems());

    }
    @FXML
    protected void onJobPotsSection(ActionEvent event) throws IOException, InvalidTypeException {
        state = "JobPost";
        mainList.setItems(null);
        mainList.setItems(agency.getActiveModels("JobPost"));
        mainList.setCellFactory(param -> {
            ListCell<Model> cell = new ListCell<Model>() {
                @Override
                protected void updateItem(Model item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getId() == null) {
                        setText(null);
                    } else {
                        setText(item.getId());
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Model c = cell.getItem();
                    try {
                        JobPostController controller = (JobPostController) ViewLoader.newWindow(
                                getClass().getResource("Job Post View.fxml"), c.getId(), null
                        );
                        controller.loadViewInfo(c.getId());
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
        System.out.println(this.mainList.getItems());

    }
    @FXML
    protected void onReport3(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Report3.fxml"), "Month Interviews by Company", null);
    }
    @FXML
    protected void onReport1(ActionEvent event) throws IOException {
        ViewLoader.newWindow(getClass().getResource("Report1.fxml"), "Most Popular", null);
    }
    @FXML
    protected void onReport2() throws IOException {
        ViewLoader.newWindow(getClass().getResource("Report2.fxml"), "Appliances by Company", null);
    }

    @FXML
    protected void onReport4() throws IOException {
        ViewLoader.newWindow(getClass().getResource("Report4.fxml"), "All Interviews for a Company", null);
    }
    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {}

    @FXML
    protected void onSearchButton() throws  IOException {}

    public void onAboutGlassWindow(ActionEvent actionEvent) {
    }
}

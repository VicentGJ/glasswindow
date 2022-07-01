package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Model;
import quantity.glasswindow.core.customExceptions.InvalidTypeException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController extends TransitionController implements Initializable {

    @FXML
    private ListView<Company> mainList;
    private Agency agency;

    private ObservableList<String> mainListItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        mainList.setItems(agency.getCompanyList());
        // Try to modify this in the future to work with Model abstract class instead of multiple concrete classes
        mainList.setCellFactory(param -> {
            ListCell<Company> cell = new ListCell<Company>() {
                @Override
                protected void updateItem(Company item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getName() == null) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Company c = cell.getItem();
                    try {
                        CompanyViewController controller = (CompanyViewController) ViewLoader.newWindow(
                                getClass().getResource("Company View.fxml"), c.getName(), null
                        );

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
        System.out.println(this.mainList.getItems());
    }

    public void updateListView(String id) throws InvalidTypeException{
        mainListItems.add(id);
        System.out.println(id);
        //mainList.setItems(null);
        //ArrayList<String> list = new ArrayList<>(agency.gerObjectListIDs("Company", new HashMap<>()));
        //list.add("mike");
        //mainList.setItems(FXCollections.observableArrayList(list));
        //System.out.println(mainList.getItems());
        //mainList.refresh();
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
    protected void onCandidatesSection(ActionEvent event) throws InvalidTypeException {
        this.updateListView(",ike");
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

package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Model;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.NameNotFoundException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Report2Controller implements Initializable {

    @FXML
    ComboBox<String> companyComboBox;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<Candidate> listView;
    private Agency agency = Agency.getInstance();
    private ObservableList<String> companyComboBoxItems = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidates = FXCollections.observableArrayList();

    private void setComboBoxItems() {
        companyComboBoxItems.clear();
        for (Company c : agency.getCompanyList()) {
            companyComboBoxItems.add(c.getName());
        }
    }

    private void setCandidates(String id, LocalDate date) {
        candidates.clear();
        try {
            ArrayList<Candidate> appliances = agency.getAppliances(id, date);
            candidates.addAll(appliances);
            System.out.println(appliances);
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBoxItems();
        companyComboBox.setItems(companyComboBoxItems);
        setCandidates(agency.getCompanyList().get(0).getId(), LocalDate.now());
        listView.setItems(candidates);
        System.out.println(candidates);
        listView.setCellFactory(param -> {
            ListCell<Candidate> cell = new ListCell<>() {
                @Override
                protected void updateItem(Candidate item, boolean empty) {
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
    }
    public void onDatePicker() throws NameNotFoundException {
        String companyID = agency.getCompanyByName(companyComboBox.getValue()).getId();
        LocalDate date = datePicker.getValue();
        setCandidates(companyID, date);
    }
    public void onBackButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

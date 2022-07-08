package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Interview;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import quantity.glasswindow.utils.InterviewDay;

public class Report3Controller implements Initializable {
    @FXML
    private ComboBox<String> companyComboBox, monthComboBox;
    @FXML private TableView tableView;

    @FXML private TableColumn interViewIDs, candidateIDs, dates;

    private ObservableList<String> companyComboBoxItems = FXCollections.observableArrayList(),
            monthItems = FXCollections.observableArrayList();
    private ObservableList<InterviewDay> interviewList = FXCollections.observableArrayList();
    private Agency agency = Agency.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Company c: agency.getCompanyList()) {
            companyComboBoxItems.add(c.getName());
        }
        for (Month m: Month.values()) {
            monthItems.add(m.name());
        }
        companyComboBox.setItems(companyComboBoxItems);
        companyComboBox.setPromptText(companyComboBoxItems.get(0));
        monthComboBox.setItems(monthItems);
        monthComboBox.setPromptText(Month.JULY.name());

        ArrayList<ArrayList<Interview>> interviewsMonth = new ArrayList<>(agency.getInterviewsMonth(
                agency.getCompanyList().get(0).getId(), Month.JULY.getValue())
        );
        System.out.println(interviewsMonth);
        for (int j = 0; j < interviewsMonth.size(); j++) {
            if (interviewsMonth.get(j) == null) {
                InterviewDay day = new InterviewDay("None", "None", j+1);
                interviewList.add(day);
            }
            else {
                for (Interview i : interviewsMonth.get(j)) {
                    InterviewDay day = new InterviewDay(i.getCandidate(), i.getJobPost(), j);
                    interviewList.add(day);
                }
            }
        }
        interViewIDs.setCellValueFactory(
                new PropertyValueFactory<InterviewDay, String>("jobPost")
        );
        candidateIDs.setCellValueFactory(
                new PropertyValueFactory<InterviewDay, String>("candidate")
        );
        dates.setCellValueFactory(
                new PropertyValueFactory<InterviewDay, Integer>("day")
        );
        tableView.setItems(interviewList);
    }
    @FXML
    public void onCompanyComboBox (ActionEvent event) {
        String companyID = companyComboBox.getValue();
        System.out.println(companyID);
    }
    @FXML
    public void onBackButton (ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

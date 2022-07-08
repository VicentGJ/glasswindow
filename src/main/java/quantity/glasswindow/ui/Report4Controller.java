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
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.NameNotFoundException;
import quantity.glasswindow.utils.InterviewDay;
import quantity.glasswindow.utils.InterviewJobPost;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Report4Controller implements Initializable {
    @FXML
    private ComboBox<String> companyComboBox;
    @FXML private TableView tableView;

    @FXML private TableColumn interViewIDs, candidateIDs, dates;

    private ObservableList<String> companyComboBoxItems = FXCollections.observableArrayList();

    private ObservableList<InterviewJobPost> interviewList = FXCollections.observableArrayList();
    private Agency agency = Agency.getInstance();

    private void setInterviewList(String companyID) throws IdNotFoundException {
        interviewList.clear();
        Company company = (Company) agency.getObject(companyID);
        ArrayList<ArrayList<Interview>> interviews = new ArrayList<>(agency.getCompanyInterviews(
                company)
        );
        System.out.println(interviews);
        for (ArrayList<Interview> interview : interviews) {
            if (interview == null) {
            } else {
                for (Interview i : interview) {
                    InterviewJobPost day = new InterviewJobPost(i.getCandidate(), i.getJobPost(), i.getDate());
                    interviewList.add(day);
                }
            }
        }
    }
    private void setComboBoxItems() {
        for (Company c: agency.getCompanyList()) {
            companyComboBoxItems.add(c.getName());
        }
        companyComboBox.setItems(companyComboBoxItems);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBoxItems();
        companyComboBox.setValue(companyComboBoxItems.get(0));
        try {
            setInterviewList(agency.getCompanyList().get(0).getId());
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
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
    public void onCompanyComboBox () {
        String companyID = companyComboBox.getValue();
        System.out.println(companyID);
        try {
            setInterviewList(agency.getCompanyByName(companyComboBox.getValue()).getId());
        } catch (NameNotFoundException | IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onMonthComboBox () {
        String companyID = companyComboBox.getValue();
        System.out.println(companyID);
        try {
            setInterviewList(agency.getCompanyByName(companyComboBox.getValue()).getId());
        } catch (NameNotFoundException | IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onBackButton (ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRefreshButton () {
        String companyID = companyComboBox.getValue();
        System.out.println(companyID);
        try {
            setComboBoxItems();
            setInterviewList(agency.getCompanyByName(companyComboBox.getValue()).getId());
        } catch (NameNotFoundException | IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

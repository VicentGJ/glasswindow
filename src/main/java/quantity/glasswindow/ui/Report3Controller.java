package quantity.glasswindow.ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Interview;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Report3Controller implements Initializable {
    @FXML
    private ComboBox<String> companyComboBox, monthComboBox;
    @FXML private TableView tableView;

    @FXML private TableColumn<Interview, String> interViewIDs, candidateIDs, dates;

    private ObservableList<String> companyComboBoxItems, monthItems;
    private ObservableList<Interview> interviewList;
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
        monthComboBox.setItems(monthItems);
        interviewList.addAll(agency.getInterviewList());
    }

    public void onCompanyComboBox (ActionEvent event) {
        String companyID = companyComboBox.getValue();
        System.out.println(companyID);
    }
}

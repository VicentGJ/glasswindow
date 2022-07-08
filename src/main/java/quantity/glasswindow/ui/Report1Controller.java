package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import quantity.glasswindow.core.*;
import quantity.glasswindow.utils.Helper;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Report1Controller implements Initializable {
    @FXML
    private ComboBox<String> typeSelector;
    @FXML
    private ComboBox<String> categorySelector;
    @FXML
    private ListView<Model> listView;

    private Agency agency = Agency.getInstance();
    private ObservableList<Model> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeSelector.setItems(FXCollections.observableArrayList(
                "JobPost",
                "Company"
        ));
        categorySelector.setItems(FXCollections.observableArrayList(
                "Globally",
                "Specialty",
                "Sector"
        ));
        HashMap<String, Integer> appliances = new HashMap<>();
        int count = 0;
        for (JobPost j: agency.getJobPostList()) {
            for (Interview i: agency.getInterviewList()) {
                if (i.getJobPost().equals(j.getId())) {
                    count++;
                }
            }
            appliances.put(j.getId(), count);
            count = 0;
        }
        appliances = Helper.sortByValue(appliances);
        System.out.println(appliances.values());
    }
}

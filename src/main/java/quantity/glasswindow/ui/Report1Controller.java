package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import quantity.glasswindow.core.*;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.utils.Helper;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
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
    private HashMap<String, Integer> appliances = new HashMap<>();
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
        for (String id: appliances.keySet()) {
            for (JobPost j: agency.getJobPostList()){
                if (j.getId().equals(id)) {
                    itemList.add(j);
                }
            }
        }
        typeSelector.setPromptText("JobPost");
        categorySelector.setPromptText("Globally");
        categorySelector.setDisable(true);
        listView.setItems(itemList);
        listView.setCellFactory(param -> {
            ListCell<Model> cell = new ListCell<>() {
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
                        if (c.getClass().getSimpleName().equals("JobPost")){
                            JobPostController controller = (JobPostController) ViewLoader.newWindow(
                                    getClass().getResource("Job Post View.fxml"), c.getId(), null
                            );
                            controller.loadViewInfo(c.getId());
                        }
                        else {
                            CompanyViewController controller = (CompanyViewController) ViewLoader.newWindow(
                                    getClass().getResource("Company View.fxml"), c.getId(), null
                            );
                            controller.loadViewInfo(c.getId());
                        }
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
    }
    @FXML
    public void onBackButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onTypeSelector (ActionEvent event) throws IdNotFoundException {
        itemList.clear();
        String selected = typeSelector.getValue();
        if (selected.equals("Company")) {
            for (String j: appliances.keySet()) {
                Model company = agency.getObject(((JobPost) agency.getObject(j)).getCompany());
                itemList.add(company);
            }
            categorySelector.setDisable(false);
        } else if (selected.equals("JobPost")) {
            for (String id: appliances.keySet()) {
                for (JobPost j: agency.getJobPostList()){
                    if (j.getId().equals(id)) {
                        itemList.add(j);
                    }
                }
            }
            categorySelector.setDisable(true);
        }
    }
    @FXML
    public void onCategorySelector (ActionEvent event) {

    }
}

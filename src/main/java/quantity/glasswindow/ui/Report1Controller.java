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
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Specialty;
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
    private ComboBox<String> sectorSelector, specialtySelector;
    @FXML
    private ListView<Model> listView;

    private Agency agency = Agency.getInstance();
    private ObservableList<Model> itemList = FXCollections.observableArrayList();
    private HashMap<String, Integer> appliances = new HashMap<>();

    private void setAppliances() {
        appliances.clear();
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
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeSelector.setItems(FXCollections.observableArrayList(
                "JobPost",
                "Company"
        ));
        ArrayList<String> sectors = new ArrayList<>();
        for (Branch b: Branch.values()) {
            sectors.add(b.name());
        }
        sectorSelector.setItems(FXCollections.observableArrayList(sectors));
        ArrayList<String> specialties = new ArrayList<>();
        for (Specialty s: Specialty.values()) {
            specialties.add(s.name());
        }
        specialtySelector.setItems(FXCollections.observableArrayList(specialties));
        setAppliances();
        typeSelector.setValue("JobPost");
        sectorSelector.setDisable(true);
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
    public void onTypeSelector () throws IdNotFoundException {
        setAppliances();
        itemList.clear();
        String selected = typeSelector.getValue();
        if (selected.equals("Company")) {
            for (String j: appliances.keySet()) {
                Model company = agency.getObject(((JobPost) agency.getObject(j)).getCompany());
                itemList.add(company);
            }
            sectorSelector.setDisable(false);
        } else if (selected.equals("JobPost")) {
            for (String id: appliances.keySet()) {
                for (JobPost j: agency.getJobPostList()){
                    if (j.getId().equals(id)) {
                        itemList.add(j);
                    }
                }
            }
            sectorSelector.setValue(null);
            sectorSelector.setDisable(true);
        }
    }
    @FXML
    public void onSectorSelector() {
        try {
            this.onTypeSelector();
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sector = sectorSelector.getValue();
        ArrayList<Model> toFilterOut = new ArrayList<>();
        for (Model model: itemList) {
            Company c = (Company) model;
            if (!c.getSector().name().equalsIgnoreCase(sector)) {
                toFilterOut.add(c);
            }
        }
        for (Model model: toFilterOut) {
            itemList.remove(model);
        }
    }

    @FXML
    public void onSpecialtySelector() {
        setAppliances();
        String specialty = specialtySelector.getValue();
        ArrayList<String> toFilterOut = new ArrayList<>();
        for (String id: appliances.keySet()) {
            for (JobPost j: agency.getJobPostList()){
                if (j.getId().equals(id)) {
                    if (!j.getSpecialty().name().equalsIgnoreCase(specialty)){
                        toFilterOut.add(j.getId());
                    }
                }
            }
        }
        for (String id: toFilterOut) {
            appliances.remove(id);
        }
        try {
            this.onTypeSelector();
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRefreshButton() {
        setAppliances();
        try {
            onTypeSelector();
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

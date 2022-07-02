package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.enumerations.Branch;

public class CompanyNewController {
    private Agency agency;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;

    public CompanyNewController () {
        this.agency = Agency.getInstance();
        try{
        // TODO: Implement proper IDs
            Company company = new Company(nameField.getText(), nameField.getText(), addressField.getText(),
                    phoneField.getText(), Branch.AGRICULTURE);
        }
        except (Exception e)
    }
    public void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSaveButton(ActionEvent event){
        String name = nameField.getText();
        String description = descriptionField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        //Company company = new Company(name);
        //agency.insertObject();
    }
}

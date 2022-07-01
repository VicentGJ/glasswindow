package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyNewController implements Initializable {
    private Agency agency;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> comboBox;

    private final ObservableList<String> comboBoxItems = FXCollections.observableArrayList(
            "Agriculture",
            "Tourism",
            "Health",
            "Education",
            "Services",
            "Industry"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        this.comboBox.setItems(comboBoxItems);
    }
    public void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSaveButton(ActionEvent event) throws IOException, InvalidTypeException {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        try{
            // TODO: Implement proper IDs
            Company company = new Company(nameField.getText(), nameField.getText(), addressField.getText(),
                    phoneField.getText(), Branch.values()[comboBoxItems.indexOf(comboBox.getValue())] );
            this.agency.insertObject(company);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (DuplicatedIDException | InvalidIDException | InvalidNameException | InvalidPhoneException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                    "Error Message.fxml"), e.getMessage(), null);
            controller.setErrorMessage();
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "No sector selected", null);
            controller.setErrorMessage("You must select one of the options in the dropdown menu");
        }
    }
}

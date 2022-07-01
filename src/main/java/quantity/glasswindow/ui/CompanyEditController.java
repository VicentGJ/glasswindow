package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class CompanyEditController extends TransitionController{
    @FXML
    private TextField nameField, addressField, phoneField;
    @FXML private ComboBox<String> sector;
    @FXML private Text header;
    private Company company;
    private Agency agency;
    private final ObservableList<String> comboBoxItems = FXCollections.observableArrayList(
            "Agriculture",
            "Tourism",
            "Health",
            "Education",
            "Services",
            "Industry"
    );
    public void loadViewInfo(String id) throws IdNotFoundException {
        agency = Agency.getInstance();
        company = (Company) agency.getObject(id);
        sector.setItems(comboBoxItems);
        header.setText(company.getName());
        nameField.setText(company.getName());
        addressField.setText(company.getAddress());
        phoneField.setText(company.getPhone());
        //sector.set(company.getSector());
    }
    @FXML
    protected void onButtonBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onButtonSave(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        try{
            // TODO: Implement proper IDs
            company.setId(name);
            company.setName(name);
            company.setAddress(address);
            company.setPhone(phone);
            if (sector.getValue() != null){
                company.setSector(Branch.values()[comboBoxItems.indexOf(sector.getValue())]);
            }
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

package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class CandidateProfileEditController extends TransitionController{
    @FXML private TextField dniField,nameField,addressField,phoneField;
    @FXML private ComboBox branchField,specialtyField,scholarshipField, genderField;
    @FXML private Spinner yearsofexpField;
    @FXML private Text header;

    private Agency agency;
    private Candidate candidate;
    private final ObservableList<String> comboBoxItemsBranch = FXCollections.observableArrayList(
            "Agriculture",
            "Tourism",
            "Health",
            "Education",
            "Services",
            "Industry"
    );
    private final ObservableList<String> comboBoxItemsSpecialty = FXCollections.observableArrayList(
            "Engineer",
            "Developer",
            "Doctor",
            "Translator",
            "Designer",
            "Architect",
            "Scientist",
            "Economist",
            "Accountant",
            "Manager",
            "Consultant",
            "Guard"
    );
    private final ObservableList<String> comboBoxItemsScholarship = FXCollections.observableArrayList(
            "Basic",
            "Grade",
            "Master",
            "PHD"
    );
    private final ObservableList<String> comboBoxItemsGender = FXCollections.observableArrayList(
            "Masculine",
            "Feminine"
    );

    public void loadViewInfo(String id) throws IdNotFoundException {
        agency = Agency.getInstance();
        candidate = (Candidate) agency.getObject(id);
        header.setText(candidate.getName());
        branchField.setItems(comboBoxItemsBranch);
        specialtyField.setItems(comboBoxItemsSpecialty);
        scholarshipField.setItems(comboBoxItemsScholarship);
        genderField.setItems(comboBoxItemsGender);
        nameField.setText(candidate.getName());
        phoneField.setText(candidate.getPhone());
        dniField.setText(candidate.getDni());
        addressField.setText(candidate.getAddress());
        yearsofexpField.getValueFactory().setValue(candidate.getYearsOfExp());
        header.setText(candidate.getName());
    }
    @FXML
    protected void onSaveButton(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String dni = dniField.getText();
        try{
            if(!candidate.getDni().equals(dni))
                candidate.setDNI(dni);//do only if its edited, so it doesn't throw exception on no edition because dni already exists
            candidate.setPhone(phone);
            candidate.setAddress(address);
            candidate.setName(name);
            candidate.setYearsOfExp(Integer.parseInt(String.valueOf(yearsofexpField.getValueFactory().getValue())));
            if (branchField.getValue() != null)
                candidate.setSector(Branch.values()[comboBoxItemsBranch.indexOf(branchField.getValue())]);
            if (specialtyField.getValue() != null)
                candidate.setSpecialty(Specialty.values()[comboBoxItemsSpecialty.indexOf(specialtyField.getValue())]);
            if (scholarshipField.getValue() != null)
                candidate.setScholarship(Scholarship.values()[comboBoxItemsScholarship.indexOf(scholarshipField.getValue())]);
            AdditionalInfoController controller = (AdditionalInfoController) ViewLoader.thisWindow(
                    getClass().getResource("Additional Info.fxml"), event);
            controller.loadViewInfo(candidate);
        }
        catch (InvalidNameException | InvalidPhoneException | InvalidIDException | DuplicatedIDException |
               InvalidYearsOfExpException e) {
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
    @FXML
    protected void onBackButton(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

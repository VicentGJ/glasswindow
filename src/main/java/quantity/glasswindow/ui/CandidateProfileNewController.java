package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import quantity.glasswindow.core.enumerations.Gender;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CandidateProfileNewController implements Initializable {
    @FXML private TextField dniField,nameField,addressField,phoneField;
    @FXML private ComboBox branchField,specialtyField,scholarshipField, genderField;
    @FXML private Spinner yearsofexpField;
    @FXML private Text header;

    private Agency agency;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        this.branchField.setItems(comboBoxItemsBranch);
        this.specialtyField.setItems(comboBoxItemsSpecialty);
        this.scholarshipField.setItems(comboBoxItemsScholarship);
        this.genderField.setItems(comboBoxItemsGender);
    }
    @FXML
    protected void onSaveButton(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String dni = dniField.getText();
        int yearsofexp = Integer.parseInt(String.valueOf(yearsofexpField.getValueFactory().getValue()));
        try{
            Branch branch = Branch.values()[comboBoxItemsBranch.indexOf(branchField.getValue())];
            Gender gender = Gender.values()[comboBoxItemsGender.indexOf(genderField.getValue())];
            Specialty specialty = Specialty.values()[comboBoxItemsSpecialty.indexOf(specialtyField.getValue())];
            Scholarship scholarship = Scholarship.values()[comboBoxItemsScholarship.indexOf(scholarshipField.getValue())];
            Candidate candidate = agency.createCandidate(dni,name,gender,address,phone,scholarship,specialty,branch,yearsofexp);
            agency.insertObject(candidate);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "No sector selected", null);
            controller.setErrorMessage("You must select one of the options in the dropdown menu");
        } catch (InvalidTypeException | InvalidIDException | DuplicatedIDException | InvalidPhoneException | InvalidNameException | InvalidYearsOfExpException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "Error", null);
            controller.setErrorMessage(e.getMessage());
        }

    }

    @FXML
    protected void onBackButton(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
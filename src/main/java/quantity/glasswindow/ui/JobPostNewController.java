package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.core.enumerations.Status;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JobPostNewController implements Initializable {
    @FXML
    private TextField companyField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Spinner salaryField;
    @FXML
    private ComboBox<String> branchField, specialtyField, scholarshipField;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.agency = Agency.getInstance();
        this.branchField.setItems(comboBoxItemsBranch);
        this.specialtyField.setItems(comboBoxItemsSpecialty);
        this.scholarshipField.setItems(comboBoxItemsScholarship);
    }
    public void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onSaveButton(ActionEvent event) throws IOException {
        String companyName = companyField.getText();
        String description = descriptionField.getText();
        float salary =  Float.parseFloat((String.valueOf(salaryField.getValueFactory().getValue())));
        try{
            String companyID = agency.getCompanyByName(companyName).getId();
            Branch branch = Branch.values()[comboBoxItemsBranch.indexOf(branchField.getValue())];
            Specialty specialty = Specialty.values()[comboBoxItemsSpecialty.indexOf(specialtyField.getValue())];
            Scholarship scholarship = Scholarship.values()[comboBoxItemsScholarship.indexOf(scholarshipField.getValue())];
            JobPost jobPost = agency.createJobPost(branch,salary,Status.OPEN,description,companyID,scholarship,specialty);
            agency.insertObject(jobPost);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "No sector selected", null);
            controller.setErrorMessage("You must select one of the options in the dropdown menu");
        } catch (NameNotFoundException | InvalidSalaryException | InvalidTypeException | ModelNotFoundException |
                 InvalidIDException | DuplicatedIDException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "Error", null);
            controller.setErrorMessage(e.getMessage());
        }
    }
}

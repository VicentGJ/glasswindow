package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class JobPostEditController extends TransitionController{
    @FXML
    private TextField companyField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Spinner salaryField;
    @FXML
    private ComboBox<String> branchField, specialtyField;

    private JobPost jobPost;
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
    public void loadViewInfo(String id) throws IdNotFoundException {
        agency = Agency.getInstance();
        jobPost = (JobPost)agency.getObject(id);
        companyField.setText(((Company)(agency.getObject(jobPost.getCompany()))).getName());
        descriptionField.setText(jobPost.getDescription());
        salaryField.setPromptText(String.valueOf(jobPost.getSalary()));
        branchField.setItems(comboBoxItemsBranch);
        specialtyField.setItems(comboBoxItemsSpecialty);
    }
    @FXML
    protected void onButtonBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onButtonSave(ActionEvent event) throws IOException {
        String companyName = companyField.getText();
        String description = descriptionField.getText();
        float salary =  Float.parseFloat((String.valueOf(salaryField.getValueFactory().getValue())));
        try{
            String companyID = agency.getCompanyByName(companyName).getId();
            jobPost.setId( agency.genID("jobpost"));
            jobPost.setSalary(salary);
            jobPost.setDescription(description);
            jobPost.setCompany(companyID);
            if (branchField.getValue() != null){
                jobPost.setBranch(Branch.values()[comboBoxItemsBranch.indexOf(branchField.getValue())]);
            }
            if (specialtyField.getValue() != null){
                jobPost.setSpecialty(Specialty.values()[comboBoxItemsSpecialty.indexOf(specialtyField.getValue())]);
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "No sector selected", null);
            controller.setErrorMessage("You must select one of the options in the dropdown menu");
        } catch (NameNotFoundException| InvalidSalaryException | InvalidTypeException | ModelNotFoundException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(
                    getClass().getResource("Error Message.fxml"), "Error", null);
            controller.setErrorMessage(e.getMessage());
        }
    }

}

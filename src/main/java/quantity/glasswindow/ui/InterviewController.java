package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.*;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InterviewController {
    private Company company;
    private JobPost jp;
    private Agency a;

    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> candidateField;

    private ObservableList<String> candidateItems = FXCollections.observableArrayList();

    public void loadViewInfo(String jpID) throws IdNotFoundException {
        a = Agency.getInstance();
        jp = (JobPost) a.getObject(jpID);
        company = (Company) a.getObject(jp.getCompany());
        ArrayList<Candidate> candidates = new ArrayList<>(a.getCandidateList());
        ArrayList<Candidate> toFilterOut = new ArrayList<>();
        for (Candidate candidate: candidates) {
            if (candidate.getSpecialty()!=jp.getSpecialty()) {
                toFilterOut.add(candidate);
            }
            if (candidate.getScholarship()!=jp.getScholarship()) {
                toFilterOut.add(candidate);
            }
            //if (candidate.getYearsOfExp()<jp.getYearsOfExp)
            if (candidate.getSector()!=jp.getBranch()) {
                toFilterOut.add(candidate);
            }
        }
        for (Candidate candidate: toFilterOut) {
            candidates.remove(candidate);
        }
        for (Candidate candidate: candidates) {
            candidateItems.add(candidate.getDni());
        }
        candidateField.setItems(candidateItems);
    }

    @FXML
    protected void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onOkButton(ActionEvent event) throws IOException {
        try {
            String candidate = a.getCandidateByDNI(candidateField.getValue()).getId();
            LocalDate date = dateField.getValue();
            Interview interview = new Interview(String.valueOf(date.getDayOfMonth()),date,candidate,company.getId(),jp.getId());
        }catch (DNINotFoundException | InvalidDateException | InvalidIDException | DuplicatedIDException e){
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                    "Error Message.fxml"), e.getMessage(), null);
            controller.setErrorMessage();
            e.printStackTrace();
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

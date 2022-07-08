package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.InvalidTypeException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.util.ArrayList;

public class CandidateProfileController extends TransitionController{
    @FXML
    private Text name,dni,gender, address, phone,scholarship,branch, specialty,yearsofexp,header;
    private Candidate candidate;
    Agency agency = Agency.getInstance();

    public void loadViewInfo(String id) throws IdNotFoundException {
        candidate = (Candidate) agency.getObject(id);
        this.dni.setText(candidate.getId());
        candidate = (Candidate) agency.getObject(id);
        name.setText(candidate.getName());
        gender.setText(candidate.getGender().name());
        scholarship.setText(candidate.getScholarship().name());
        branch.setText(candidate.getSector().name());
        specialty.setText(candidate.getSpecialty().name());
        address.setText(candidate.getAddress());
        yearsofexp.setText(String.valueOf(candidate.getYearsOfExp()));
        phone.setText(candidate.getPhone());
        header.setText(candidate.getName());
    }

    @FXML
    public void onBackButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onEditButton(ActionEvent event) throws IOException, IdNotFoundException {
        CandidateProfileEditController controller = (CandidateProfileEditController) ViewLoader.thisWindow(
                getClass().getResource("Candidate Edit.fxml"), event);
        controller.loadViewInfo(candidate.getId());
    }

    @FXML
    public void onDeleteButton(ActionEvent event) throws IOException {
        Agency agency = Agency.getInstance();
        try {
            agency.deleteObject(candidate.getId());
        } catch (IdNotFoundException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                    "Error Message.fxml"), e.getMessage(), null);
            controller.setErrorMessage();
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSeekButton(ActionEvent event) {
        try {
            ArrayList<JobPost> jobPosts = agency.getQualifiedJobPostList(candidate);
            if (jobPosts.isEmpty()) {
                ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                        "Error Message.fxml"), "No Job Posts", null
                );
                controller.setErrorMessage("No Job Posts available for this candidate");
            } else {
                AvailableJobController controller = (AvailableJobController) ViewLoader.thisWindow(
                        getClass().getResource("Available JobPosts.fxml"), event);
                controller.loadInfo(candidate.getDni());
            }
        } catch (InvalidTypeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

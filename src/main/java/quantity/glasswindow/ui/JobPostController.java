package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class JobPostController extends TransitionController{
    @FXML
    private Text description,company,salary,branch;
    private JobPost jp;

    public void loadViewInfo(String id) throws IdNotFoundException {
        Agency agency = Agency.getInstance();
        jp = (JobPost) agency.getObject(id);
        company.setText(jp.getCompany());
        description.setText(jp.getDescription());
        branch.setText(jp.getBranch().name());
        salary.setText(String.valueOf(jp.getSalary()));
    }
    @FXML
    protected void onBackButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onEditButton(ActionEvent event) throws IOException, IdNotFoundException {
        JobPostEditController controller = (JobPostEditController) ViewLoader.thisWindow(
                getClass().getResource("Job Post Edit.fxml"), event);
        controller.loadViewInfo(jp.getId());

    }
    @FXML
    protected void onApplyButton(ActionEvent event) throws IOException, IdNotFoundException {
        InterviewController controller = (InterviewController) ViewLoader.thisWindow(
                getClass().getResource("Arrange Interview.fxml"), event);
        controller.loadViewInfo(jp.getId());
    }
    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {
        Agency agency = Agency.getInstance();
        try{
            agency.deleteObject(jp.getId());
        } catch (IdNotFoundException e) {
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                    "Error Message.fxml"), e.getMessage(), null);
            controller.setErrorMessage();
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

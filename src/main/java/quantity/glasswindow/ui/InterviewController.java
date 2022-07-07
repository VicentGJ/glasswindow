package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.Interview;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.customExceptions.DuplicatedIDException;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.InvalidDateException;
import quantity.glasswindow.core.customExceptions.InvalidIDException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.time.LocalDate;

public class InterviewController {
    private Company company;
    private JobPost jp;
    private Agency a;

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField candidateField;
    /*add h m ampm*/

    public void loadViewInfo(String jpID) throws IdNotFoundException {
        a = Agency.getInstance();
        jp = (JobPost) a.getObject(jpID);
        company = (Company) a.getObject(jp.getCompany());
    }

    @FXML
    protected void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onOkButton(ActionEvent event) throws IOException {
        try {
            String candidate = a.getObject(candidateField.getText()).getId();
            LocalDate date = dateField.getValue();
            Interview interview = new Interview(String.valueOf(date.getDayOfMonth()),date,candidate,company.getId(),jp.getId());
        }catch (IdNotFoundException | InvalidDateException | InvalidIDException | DuplicatedIDException e){
            ErrorMessageController controller = (ErrorMessageController) ViewLoader.newWindow(getClass().getResource(
                    "Error Message.fxml"), e.getMessage(), null);
            controller.setErrorMessage();
            e.printStackTrace();
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

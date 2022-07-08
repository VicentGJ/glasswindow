package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.JobPost;
import quantity.glasswindow.core.Model;
import quantity.glasswindow.core.customExceptions.DNINotFoundException;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.customExceptions.InvalidTypeException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class AvailableJobController {
    @FXML
    ListView<JobPost> listView;

    private Agency agency = Agency.getInstance();
    private ObservableList<JobPost> jobPostObservableList = FXCollections.observableArrayList();

    public void loadInfo(String candidateDNI) {
        try {
            Candidate candidate = agency.getCandidateByDNI(candidateDNI);
            jobPostObservableList.addAll(agency.getQualifiedJobPostList(candidate));
        } catch (DNINotFoundException | InvalidTypeException e) {
            throw new RuntimeException(e);
        }
        listView.setItems(jobPostObservableList);
        listView.setCellFactory(param -> {
            ListCell<JobPost> cell = new ListCell<>() {
                @Override
                protected void updateItem(JobPost item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getId() == null) {
                        setText(null);
                    } else {
                        setText(item.getId());
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (cell.getItem() != null) {
                    Model c = cell.getItem();
                    try {
                        JobPostController controller = (JobPostController) ViewLoader.newWindow(
                                getClass().getResource("Job Post View.fxml"), c.getId(), null
                        );
                        controller.loadViewInfo(c.getId());
                    } catch (IOException | IdNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            return cell;
        });
    }

    public void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

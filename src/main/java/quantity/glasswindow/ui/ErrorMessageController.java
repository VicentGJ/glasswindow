package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorMessageController {

    @FXML
    private Text messageField;
    @FXML private VBox container;

    public void setErrorMessage() {
        Stage stage = (Stage) (container).getScene().getWindow();
        String message = stage.getTitle();
        this.messageField.setText(message);
    }

    public void setErrorMessage(String errorMessage) {
        this.messageField.setText(errorMessage);
    }
    public void onOkButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

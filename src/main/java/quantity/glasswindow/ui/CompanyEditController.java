package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.enumerations.Branch;

public class CompanyEditController extends TransitionController{
    @FXML
    private TextField name, address, phone;
    @FXML private ComboBox<Branch> sector;
    private Company company;
    public void loadViewInfo(String id) throws IdNotFoundException {
        Agency agency = Agency.getInstance();
        company = (Company) agency.getObject(id);
        name.setText(company.getName());
        address.setText(company.getAddress());
        phone.setText(company.getPhone());
        //sector.set(company.getSector());
    }
    @FXML
    protected void onButtonBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onButtonSave(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}

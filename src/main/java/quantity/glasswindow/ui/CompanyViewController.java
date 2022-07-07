package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;

public class CompanyViewController{
    @FXML
    private Text name, address, phone, sector;
    private Company company;
    public void loadViewInfo(String id) throws IdNotFoundException {
        Agency agency = Agency.getInstance();
        company = (Company) agency.getObject(id);
        name.setText(company.getName());
        address.setText(company.getAddress());
        phone.setText(company.getPhone());
        sector.setText(company.getSector().name());
    }
    @FXML
    protected void onBackButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onEditButton(ActionEvent event) throws IOException, IdNotFoundException {
        CompanyEditController controller = (CompanyEditController) ViewLoader.thisWindow(
                getClass().getResource("Company Edit.fxml"), event);
        controller.loadViewInfo(company.getId());
    }

    @FXML
    protected void onDeleteButton(ActionEvent event) throws IOException {
        Agency agency = Agency.getInstance();
        try {
            agency.deleteObject(company.getId());
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

package quantity.glasswindow.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Company;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.utils.ViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        System.out.println(company.getPhone());
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
}

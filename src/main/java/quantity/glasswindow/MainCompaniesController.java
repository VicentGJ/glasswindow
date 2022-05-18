package quantity.glasswindow;

import javafx.event.ActionEvent;
import quantity.glasswindow.utils.WindowLoader;

import java.io.IOException;

public class MainCompaniesController extends  MainController{
    @Override
    protected void onEditButton(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Company Edit.fxml"), "Edit Company", null);
    }

    @Override
    protected void onDeleteButton(ActionEvent event) throws IOException {

    }

    @Override
    protected void onEntityLink(ActionEvent event) throws IOException {
        WindowLoader.loadWindow(getClass().getResource("Company View.fxml"), "Edit Company", null);
    }

    @Override
    protected void onSearchButton() throws IOException {
    }
}

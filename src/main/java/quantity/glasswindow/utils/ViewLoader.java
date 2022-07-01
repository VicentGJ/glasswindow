package quantity.glasswindow.utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ViewLoader {
    public static Object newWindow(URL loc, String title, Stage parentStage) throws IOException {
        Object controller = null;
        FXMLLoader loader = new FXMLLoader(loc);
        Parent parent = loader.load();
        controller = loader.getController();
        Stage stage = null;
        stage = Objects.requireNonNullElseGet(parentStage, () -> new Stage(StageStyle.DECORATED));
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
        return controller;
    }
    public static Object thisWindow(URL fxmlResource, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent next_page_parent = loader.load();
        Object controller = loader.getController();
        Scene next_page_scene = new Scene(next_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(next_page_scene);
        app_stage.show();
        return controller;
    }
}

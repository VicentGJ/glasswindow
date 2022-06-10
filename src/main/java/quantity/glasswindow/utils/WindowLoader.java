package quantity.glasswindow.utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class WindowLoader {
    public static Object loadWindow(URL loc, String title, Stage parentStage) throws IOException {
        Object controller = null;
        FXMLLoader loader = new FXMLLoader(loc);
        Parent parent = loader.load();
        controller = loader.getController();
        Stage stage = null;
        if (parentStage != null) {
            stage = parentStage;
        } else {
            stage = new Stage(StageStyle.DECORATED);
        }
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
        // setStageIcon(stage);
        return controller;
    }
}

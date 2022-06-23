package quantity.glasswindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quantity.glasswindow.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ui/main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Glass Window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Agency agency = Agency.create();
        try {
            agency.initTestData();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getSimpleName());
            System.exit(1);
        }
        launch();
    }
}
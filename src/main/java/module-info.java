module quantity.glasswindow {
    requires javafx.controls;
    requires com.jfoenix;
    requires javafx.fxml;
    requires java.logging;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.display;

    opens quantity.glasswindow to javafx.fxml;
    opens quantity.glasswindow.ui to javafx.fxml;
    exports quantity.glasswindow;
    exports quantity.glasswindow.utils;
    exports quantity.glasswindow.ui;
}